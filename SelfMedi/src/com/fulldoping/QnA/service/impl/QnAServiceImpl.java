package com.fulldoping.QnA.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fulldoping.QnA.dao.face.QnADao;
import com.fulldoping.QnA.dao.impl.QnADaoImpl;
import com.fulldoping.QnA.dto.QnA;
import com.fulldoping.QnA.dto.QnAComments;
import com.fulldoping.QnA.dto.QnADeclare;
import com.fulldoping.QnA.dto.QnAFile;
import com.fulldoping.QnA.paging.Paging;
import com.fulldoping.QnA.service.face.QnAService;
import com.fulldoping.common.JDBCTemplate;

public class QnAServiceImpl implements QnAService {
	
	//QnADao 객체 생성
	private QnADao qnaDao = new QnADaoImpl();

	@Override
	public List<QnA> getList() {
		//게시글 전체 조회 결과 처리
		return qnaDao.selectAll(JDBCTemplate.getConnection());
	}

	@Override
	public List<QnA> getList(Paging paging) {
		//게시글 전체 조회 결과 처리 - 페이징 추가
		return qnaDao.selectAll(JDBCTemplate.getConnection(), paging);		
	}

	@Override
	public Paging getPaging(HttpServletRequest req) {
		//전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}
		
		//Board 테이블의 총 게시글 수를 조회한다
		int totalCount = qnaDao.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging객체 생성
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}

	@Override
	public QnA getBoardno(HttpServletRequest req) {
		//boardno를 저장할 객체 생성
		QnA boardno = new QnA();
		
		//boardno 전달파라미터 검증 - null, ""
		String param = req.getParameter("boardNo");
		if(param!=null && !"".equals(param)) {
			
			//boardno 전달파라미터 추출
			boardno.setBoardNo( Integer.parseInt(param) );
		}
		
		//결과 객체 반환
		return boardno;
	}

	@Override
	public QnA getview(QnA boardno) {
		Connection conn = JDBCTemplate.getConnection();

		//조회수 증가
		if( qnaDao.updateHit(conn, boardno) == 1 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//게시글 조회
		QnA board = qnaDao.selectBoardByBoardno(conn, boardno); 
		
		return board;
	}

	@Override
	public String getNick(QnA viewBoard) {
		System.out.println(viewBoard.toString());
		
		return qnaDao.selectNickByUserid(JDBCTemplate.getConnection(), viewBoard);
	}

	@Override
	public void insert(HttpServletRequest req) {
		//게시글 정보 DTO 객체
		QnA board = null;
		
		//첨부파일 정보 DTO 객체
		QnAFile boardFile = null;
				
		//파일업로드 형태의 데이터가 맞는지 검사
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if( !isMultipart ) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");
			
			return; //insert() 메소드 중단
		}
		
		//게시글 정보를 저장할 DTO객체 생성
		board = new QnA();
		
		
		//디스크기반 아이템 팩토리
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//메모리 처리 사이즈 지정
		factory.setSizeThreshold(1 * 1024 * 1024); //1MB

		//임시 저장소 설정
		File repository = new File(req.getServletContext().getRealPath("tmp"));
		repository.mkdir(); //임시 저장소 폴더 생성
		factory.setRepository(repository); //임시 저장소 폴더 지정
		
		
		//파일업로드 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);

		//업로드 용량 제한
		upload.setFileSizeMax(10 * 1024 * 1024); //10MB

		
		//전달 데이터 파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		//파싱된 전달파라미터를 처리할 반복자
		Iterator<FileItem> iter = items.iterator();

		while( iter.hasNext() ) { //모든 요청 정보 처리
			FileItem item = iter.next();
	
			//--- 1) 빈 파일에 대한 처리 ---
			if( item.getSize() <= 0 ) {
				continue; //빈 파일은 무시하고 다음 FileItem처리로 넘긴다
			}
						
			//--- 2) form-data에 대한 처리 ---
			if( item.isFormField() ) {
				//키 추출하기
				String key = item.getFieldName();
				
				//값 추출하기
				String value = null;
				try {
					value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}

				//키(name)에 따라서 value저장하기
				if( "title".equals(key) ) {
					board.setBoardTitle( value );
				} else if( "content".equals(key) ) {
					board.setBoardContent( value );
				}
				
			} //if( item.isFormField() ) end
			
			//--- 3) 파일에 대한 처리 ---
			if( !item.isFormField() ) {
				
				//UUID 생성
				UUID uuid = UUID.randomUUID(); //랜덤 UUID
				String uid = uuid.toString().split("-")[0]; //8자리 uuid
				
				//로컬 저장소의 업로드 폴더
				File upFolder = new File(req.getServletContext().getRealPath("upload"));
				upFolder.mkdir(); //폴더 생성
				
				//업로드 파일 객체
				String origin = item.getName(); //원본파일명
				String stored = origin + "_" + uid; //원본파일명_uid
				File up = new File(upFolder, stored);
				
				try {
					item.write(up); //실제 업로드(임시파일을 최종결과파일로 생성함)
					item.delete(); //임시파일을 삭제
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//업로드된 파일의 정보 저장
				boardFile = new QnAFile();
				boardFile.setOriginName(origin);
				boardFile.setStoredName(stored);
				boardFile.setFileSize( (int)item.getSize() );
				
			} //if( !item.isFormField() ) end
		} //while( iter.hasNext() ) end
		
		
		//DB연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 번호 생성 - DAO 이용
		int boardno = qnaDao.selectNextBoardno(conn);
		
		//게시글 정보가 있을 경우
		if(board != null) {
			
			//작성자 userid 입력
			board.setUserNo((Integer)req.getSession().getAttribute("userNo"));
			board.setUserId( qnaDao.getUserId(conn, (Integer)req.getSession().getAttribute("userNo")) );
			board.setUserNick((String) req.getSession().getAttribute("userNick"));
			board.setBoardNo(boardno); //게시글 번호 입력 (PK)
			
			if(board.getBoardTitle()==null || "".equals(board.getBoardTitle())) {
				board.setBoardTitle("(제목없음)");
			}
			
			System.out.println("데이터 준비 완료");
			
			if( qnaDao.insert(conn, board) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		//첨부파일 정보가 있을 경우
		if(boardFile != null) {
			boardFile.setBoardNo(boardno); //게시글 번호 입력 (FK)
			
			if( qnaDao.selectinsertFile(conn, boardFile) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
	}

	@Override
	public void update(HttpServletRequest req) {
		//게시글 정보 DTO 객체
		QnA board = null;
		
		//첨부파일 정보 DTO 객체
		QnAFile boardFile = null;
		
		//파일업로드 형태의 데이터가 맞는지 검사
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if( !isMultipart ) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");
			
			return; //update() 메소드 중단
		}
		
		//게시글 정보를 저장할 DTO객체 생성
		board = new QnA();
		
		//디스크기반 아이템 팩토리
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//메모리 처리 사이즈 지정
		factory.setSizeThreshold(1 * 1024 * 1024); //1MB

		//임시 저장소 설정
		File repository = new File(req.getServletContext().getRealPath("tmp"));
		repository.mkdir(); //임시 저장소 폴더 생성
		factory.setRepository(repository); //임시 저장소 폴더 지정
		
		//파일업로드 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);

		//업로드 용량 제한
		upload.setFileSizeMax(10 * 1024 * 1024); //10MB
		
		//전달 데이터 파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		//파싱된 전달파라미터를 처리할 반복자
		Iterator<FileItem> iter = items.iterator();

		while( iter.hasNext() ) { //모든 요청 정보 처리
			FileItem item = iter.next();	
		
			//--- 1) 빈 파일에 대한 처리 ---
			if( item.getSize() <= 0 ) {
				continue; //빈 파일은 무시하고 다음 FileItem처리로 넘긴다
			}
			
			//--- 2) form-data에 대한 처리 ---
			if( item.isFormField() ) {
				//키 추출하기
				String key = item.getFieldName();
				
				//값 추출하기
				String value = null;
				try {
					value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}

				//키(name)에 따라서 value저장하기
				if( "boardno".equals(key) ) {
					board.setBoardNo( Integer.parseInt(value) );
				} else if( "title".equals(key) ) {
					board.setBoardTitle( value );
				} else if( "content".equals(key) ) {
					board.setBoardContent( value );
				}
				
			} //if( item.isFormField() ) end
			
			
			//--- 3) 파일에 대한 처리 ---
			if( !item.isFormField() ) {
				
				//UUID 생성
				UUID uuid = UUID.randomUUID(); //랜덤 UUID
				String uid = uuid.toString().split("-")[0]; //8자리 uuid
				
				//로컬 저장소의 업로드 폴더
				File upFolder = new File(req.getServletContext().getRealPath("upload"));
				upFolder.mkdir(); //폴더 생성
				
				//업로드 파일 객체
				String origin = item.getName(); //원본파일명
				String stored = origin + "_" + uid; //원본파일명_uid
				File up = new File(upFolder, stored);
				
				
				try {
					item.write(up); //실제 업로드(임시파일을 최종결과파일로 생성함)
					item.delete(); //임시파일을 삭제
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//업로드된 파일의 정보 저장
				boardFile = new QnAFile();
				boardFile.setOriginName(origin);
				boardFile.setStoredName(stored);
				boardFile.setFileSize( (int)item.getSize() );
				
			} //if( !item.isFormField() ) end
		} //while( iter.hasNext() ) end
		
		//DB연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 정보가 있을 경우
		if(board != null) {
			if( qnaDao.update(conn, board) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		//첨부파일 정보가 있을 경우
		if(boardFile != null) {
			
			if( qnaDao.deleteFile(conn, board) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
			
			boardFile.setBoardNo(board.getBoardNo()); //게시글 번호 입력 (FK)
			
			if( qnaDao.selectinsertFile(conn, boardFile) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		//첨부파일 정보가 없을 경우
		if(boardFile == null) {
			
			if( qnaDao.deleteFile(conn, board) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
	}

	@Override
	public void delete(QnA board) {
		Connection conn = JDBCTemplate.getConnection();
		
		if( qnaDao.commentsdelete(conn, board) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		if( qnaDao.deleteFile(conn, board) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		if( qnaDao.delete(conn, board) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

	@Override
	public QnAFile getviewFile(QnA viewBoard) {
		return qnaDao.File(JDBCTemplate.getConnection(), viewBoard);
	}
	
	public List<QnAComments> getCommentList(int boardno) {
		int boardnum = boardno;
		
		//게시글 전체 조회 결과 처리
		return qnaDao.selectAllComments(JDBCTemplate.getConnection(), boardnum);
	}
	
	@Override
	public int commentInsert(HttpServletRequest req) {
		
		QnAComments comment = new QnAComments();
		Connection conn = JDBCTemplate.getConnection();
		
		//commentNo 추가
		int commentno = qnaDao.selectNextCommentno(conn);
		comment.setCommentNo(commentno);
		comment.setBoardNo(Integer.parseInt(req.getParameter("boardNo")));
		comment.setUserNo((Integer)req.getSession().getAttribute("userNo"));
		comment.setUserNick(qnaDao.getUserNick(conn, (Integer)req.getSession().getAttribute("userNo")));
		comment.setCommentContent( req.getParameter("content") );

		if( qnaDao.commentsinsert(conn, comment) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}		
		
		return commentno;
	}

	@Override
	public QnAComments getCommentno(HttpServletRequest req) {

		QnAComments comment = new QnAComments();
		Connection conn = JDBCTemplate.getConnection();
		
		String param = req.getParameter("commentNo");
		if(param!=null && !"".equals(param)) {
			
			comment.setCommentNo(Integer.parseInt(param));
		}
		
		comment = qnaDao.selectcommentBycommentno(conn, comment);
		
		//결과 객체 반환
		return comment;
	}
	
	@Override
	public QnAComments getUpdateComment(HttpServletRequest req) {
		
		QnAComments comment = new QnAComments();
		
		String param = req.getParameter("commentNo");
		if(param!=null && !"".equals(param)) {
			comment.setCommentNo(Integer.parseInt(param));
		}

		comment.setCommentContent( req.getParameter("commentContent") );
		
		//결과 객체 반환
		return comment;
	}
	
	@Override
	public QnAComments getComment(int commentno) {

		QnAComments comment = new QnAComments();
		Connection conn = JDBCTemplate.getConnection();
		
		comment.setCommentNo(commentno);
		comment = qnaDao.selectcommentBycommentno(conn, comment);
		
		//결과 객체 반환
		return comment;
	}
	
	@Override
	public void commentUpdate(HttpServletRequest req) {
		QnAComments comment = new QnAComments();
		Connection conn = JDBCTemplate.getConnection();
		
		//commentNo 추가
		comment.setCommentNo(Integer.parseInt(req.getParameter("commentNo")));
		comment.setCommentContent( req.getParameter("commentContent") );

		
		System.out.println("Comment Update : " + comment);
		if( qnaDao.commentsupdate(conn, comment) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}	
	}

	@Override
	public void commentDelete(QnAComments comment) {
		Connection conn = JDBCTemplate.getConnection();
		
		if( qnaDao.commentsdelete(conn, comment) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

	@Override
	public void declare(HttpServletRequest req) {
		
		//게시글 정보 DTO 객체
		QnA qna = null;
		
		//첨부파일 정보 DTO 객체
		QnAFile qnaFile = null;
		
		//파일업로드 형태의 데이터가 맞는지 검사
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if( !isMultipart ) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");
			return; //write() 메소드 중단
		}
		
		//게시글 정보를 저장할 DTO객체 생성
		QnADeclare qnaDeclare = new QnADeclare();
		
		//디스크기반 아이템 팩토리
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//메모리 처리 사이즈 지정
		factory.setSizeThreshold(1 * 1024 * 1024); //1MB

		//임시 저장소 설정
		File repository = new File(req.getServletContext().getRealPath("tmp"));
		repository.mkdir(); //임시 저장소 폴더 생성
		factory.setRepository(repository); //임시 저장소 폴더 지정
		
		//파일업로드 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);

		//업로드 용량 제한
		upload.setFileSizeMax(10 * 1024 * 1024); //10MB
		
		//전달 데이터 파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		//파싱된 전달파라미터를 처리할 반복자
		Iterator<FileItem> iter = items.iterator();

		while( iter.hasNext() ) { //모든 요청 정보 처리
			FileItem item = iter.next();

			//--- 1) 빈 파일에 대한 처리 ---
			if( item.getSize() <= 0 ) {
				continue; //빈 파일은 무시하고 다음 FileItem처리로 넘긴다
			}
			
			//--- 2) form-data에 대한 처리 ---
			if( item.isFormField() ) {
				//키 추출하기
				String key = item.getFieldName();
				
				//값 추출하기
				String value = null;
				try {
					value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}

				//키(name)에 따라서 value저장하기
				if( "boardNo".equals(key) ) {
					qnaDeclare.setBoardNo( Integer.parseInt(value) );
				} else if( "boardTitle".equals(key) ) {
					qnaDeclare.setBoardTitle( value );
				} else if( "userId".equals(key) ) {
					qnaDeclare.setUserId( value );
				} else if( "userNo".equals(key) ) {
					qnaDeclare.setUserNo( ( Integer.parseInt(value) ) );
				}	else if( "boardContent".equals(key) ) {
					qnaDeclare.setBoardContent( value );
				} else if( "reason".equals(key) ) {
					qnaDeclare.setReason( value );
				}  else if( "userNick".equals(key) ) {
					qnaDeclare.setUserNick( value );
				} else if( "hit".equals(key) ) {
					qnaDeclare.setHit( Integer.parseInt(value) );
				}
				
				System.out.println("[확인] qnaDeclare  :::" + qnaDeclare);
			} //if( item.isFormField() ) end
		
			//--- 3) 파일에 대한 처리 ---
			if( !item.isFormField() ) {
				
				//UUID 생성
				UUID uuid = UUID.randomUUID(); //랜덤 UUID
				String uid = uuid.toString().split("-")[0]; //8자리 uuid
				
				//로컬 저장소의 업로드 폴더
				File upFolder = new File(req.getServletContext().getRealPath("upload"));
				upFolder.mkdir(); //폴더 생성
				
				//업로드 파일 객체
				String origin = item.getName(); //원본파일명
				String stored = origin + "_" + uid; //원본파일명_uid
				File up = new File(upFolder, stored);
				
				
				
				try {
					item.write(up); //실제 업로드(임시파일을 최종결과파일로 생성함)
					item.delete(); //임시파일을 삭제
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
				//업로드된 파일의 정보 저장
				qnaFile = new QnAFile();
				qnaFile.setOriginName(origin);
				qnaFile.setStoredName(stored);
				qnaFile.setFileSize( (int)item.getSize() );
				
			} //if( !item.isFormField() ) end
		} //while( iter.hasNext() ) end
			
		//DB연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 정보가 있을 경우
		if(qnaDeclare != null) {
			if( qnaDao.declare(conn, qnaDeclare) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		//첨부파일 정보가 있을 경우
		if(qnaFile != null) {
			qnaFile.setBoardNo(qnaDeclare.getBoardNo()); //게시글 번호 입력 
			
			if( qnaDao.selectinsertFile(conn, qnaFile) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
	}

}
