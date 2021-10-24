package com.fulldoping.free.service.impl;

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

import com.fulldoping.common.JDBCTemplate;
import com.fulldoping.free.dao.face.FreeDao;
import com.fulldoping.free.dao.impl.FreeDaoImpl;
import com.fulldoping.free.dto.Free;
import com.fulldoping.free.dto.FreeComments;
import com.fulldoping.free.dto.FreeDeclare;
import com.fulldoping.free.dto.FreeFile;
import com.fulldoping.free.paging.FreePaging;
import com.fulldoping.free.service.face.FreeService;


public class FreeServiceImpl implements FreeService {

	//FreeDao 객체 생성
	private FreeDao freeDao = new FreeDaoImpl();
	
	@Override
	public List<Free> getList(FreePaging paging) {
		
		//게시글 전체 조회 결과 처리 - 페이징 추가
		return freeDao.selectAll(JDBCTemplate.getConnection(), paging);
	}
	
	@Override
	public FreePaging getPaging(HttpServletRequest req) {
		
		//전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}
		
		//Free 테이블의 총 게시글 수를 조회한다
		int totalCount = freeDao.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging객체 생성
		FreePaging paging = new FreePaging(totalCount, curPage);
		
		return paging;
	}
	
	@Override
	public Free getboardNo(HttpServletRequest req) {
		
		//boardNo를 저장할 객체 생성
		Free boardNo = new Free();	
		
		//boardNo 전달파라미터 검증 - null, ""
		String param = req.getParameter("boardNo");
		if(param!=null && !"".equals(param)) {
			
			//boardNo 전달파라미터 추출
			boardNo.setBoardNo( Integer.parseInt(param) );
		}
		
		//결과 객체 반환
		return boardNo;
		
	}
		
	@Override
	public Free view(Free boardNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		//조회수 증가
		if( freeDao.updateHit(conn, boardNo) == 1 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//게시글 조회
		Free free = freeDao.selectFreeByBoardNo(conn, boardNo); 
			
		return free;
	}
	
	
	@Override
	public String getuserNick(Free viewFree) {
		return freeDao.selectuserNickByUserId(JDBCTemplate.getConnection(), viewFree);
	}
	
	//------------- 여기까지 구현 확인
	
	@Override
	public void write(HttpServletRequest req) {
		
		//게시글 정보 DTO 객체
		Free free = null;
		
		//첨부파일 정보 DTO 객체
		FreeFile freeFile = null;
		
		//파일업로드 형태의 데이터가 맞는지 검사
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if( !isMultipart ) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");
			
			return; //write() 메소드 중단
		}
		
		//게시글 정보를 저장할 DTO객체 생성
		free = new Free();
		
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
					free.setBoardTitle( value );
				} else if( "content".equals(key) ) {
					free.setBoardContent( value );
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
				freeFile = new FreeFile();
				freeFile.setOriginName(origin);
				freeFile.setStoredName(stored);
				freeFile.setFileSize( (int)item.getSize() );
				
			} //if( !item.isFormField() ) end
		} //while( iter.hasNext() ) end
			
		//DB연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 번호 생성 - DAO 이용
		int boardNo = freeDao.selectNextBoardNo(conn);
		
		//게시글 정보가 있을 경우
		if(free != null) {
			
			//작성자 userId 입력
//			free.setUserId( (String)req.getSession().getAttribute("userid") );
			
			free.setUserId( freeDao.getUserId(conn, (Integer)req.getSession().getAttribute("userNo")) );
			
//			//작성자 userNo 입력
			free.setUserNo( (int) req.getSession().getAttribute("userNo") ); //userNO (PK)
			
			free.setUserNick( (String) req.getSession().getAttribute("userNick") ); //userNO (PK)
			
			//게시글 번호입력
			free.setBoardNo(boardNo); 
			
			if(free.getBoardTitle()==null || "".equals(free.getBoardTitle())) {
				free.setBoardTitle("(제목없음)");
			}
			
			if( freeDao.insert(conn, free) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		//첨부파일 정보가 있을 경우
		if(freeFile != null) {
			freeFile.setBoardNo(boardNo); //게시글 번호 입력
			
			if( freeDao.insertFile(conn, freeFile) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
	}
	
	@Override
	public FreeFile viewFile(Free viewFree) {
		
		return freeDao.selectFile(JDBCTemplate.getConnection(), viewFree);
	}
	
	@Override
	public void update(HttpServletRequest req) {
		//게시글 정보 DTO 객체
		Free free = null;
		
		//첨부파일 정보 DTO 객체
		FreeFile freeFile = null;
		
		//파일업로드 형태의 데이터가 맞는지 검사
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
				
		if( !isMultipart ) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");
					
			return; //write() 메소드 중단
		}	
	
		//게시글 정보를 저장할 DTO객체 생성
		free = new Free();
		
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
					free.setBoardNo( Integer.parseInt(value) );
				} else if( "title".equals(key) ) {
					free.setBoardTitle( value );
				} else if( "content".equals(key) ) {
					free.setBoardContent( value );
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
				freeFile = new FreeFile();
				freeFile.setOriginName(origin);
				freeFile.setStoredName(stored);
				freeFile.setFileSize( (int)item.getSize() );
				
			} //if( !item.isFormField() ) end
		} //while( iter.hasNext() ) end
		
		
		
		
		//DB연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 정보가 있을 경우
		if(free != null) {
						
			if( freeDao.update(conn, free) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		//첨부파일 정보가 있을 경우
		if(freeFile != null) {
			freeFile.setBoardNo(free.getBoardNo()); //게시글 번호 입력 
			
			if( freeDao.deleteFile(conn, free) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
			
			if( freeDao.insertFile(conn, freeFile) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		//첨부파일 정보가 없을 경우
		if(freeFile == null) {
			
			if( freeDao.deleteFile(conn, free) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
	}	
		

	@Override
	public void delete(Free free) {
		Connection conn = JDBCTemplate.getConnection();
		
		if( freeDao.commentsdelete(conn, free) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		if( freeDao.deleteFile(conn, free) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		if( freeDao.delete(conn, free) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}
		
	@Override
	public void declare(HttpServletRequest req) {	
		
		//게시글 정보 DTO 객체
		Free free = null;
		
		//첨부파일 정보 DTO 객체
		FreeFile freeFile = null;
		
		//파일업로드 형태의 데이터가 맞는지 검사
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if( !isMultipart ) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");
			return; //write() 메소드 중단
		}
		
		//게시글 정보를 저장할 DTO객체 생성
		FreeDeclare freeDeclare = new FreeDeclare();
		
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
					freeDeclare.setBoardNo( Integer.parseInt(value) );
				} else if( "boardTitle".equals(key) ) {
					freeDeclare.setBoardTitle( value );
				} else if( "userId".equals(key) ) {
					freeDeclare.setUserId( value );
				} else if( "userNo".equals(key) ) {
					freeDeclare.setUserNo( ( Integer.parseInt(value) ) );
				}	else if( "boardContent".equals(key) ) {
					freeDeclare.setBoardContent( value );
				} else if( "reason".equals(key) ) {
					freeDeclare.setReason( value );
				}  else if( "userNick".equals(key) ) {
					freeDeclare.setUserNick( value );
				} else if( "hit".equals(key) ) {
					freeDeclare.setHit( Integer.parseInt(value) );
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
				freeFile = new FreeFile();
				freeFile.setOriginName(origin);
				freeFile.setStoredName(stored);
				freeFile.setFileSize( (int)item.getSize() );
				
			} //if( !item.isFormField() ) end
		} //while( iter.hasNext() ) end
			
		//DB연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 정보가 있을 경우
		if(freeDeclare != null) {
			if( freeDao.declare(conn, freeDeclare) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		//첨부파일 정보가 있을 경우
		if(freeFile != null) {
			freeFile.setBoardNo(freeDeclare.getBoardNo()); //게시글 번호 입력 
			
			if( freeDao.insertFile(conn, freeFile) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
	}	

	@Override
	public List<FreeComments> getCommentList(int boardno) {
		int boardnum = boardno;
		
		//게시글 전체 조회 결과 처리
		return freeDao.selectAllComments(JDBCTemplate.getConnection(), boardnum);
	}
	
	@Override
	public int commentInsert(HttpServletRequest req) {
		
		FreeComments comment = new FreeComments();
		Connection conn = JDBCTemplate.getConnection();
		
		//commentNo 추가
		int commentno = freeDao.selectNextCommentno(conn);
		comment.setCommentNo(commentno);
		comment.setBoardNo(Integer.parseInt(req.getParameter("boardNo")));
		comment.setUserNo((Integer)req.getSession().getAttribute("userNo"));
		comment.setUserNick(freeDao.getUserNick(conn, (Integer)req.getSession().getAttribute("userNo")));
		comment.setCommentContent( req.getParameter("content") );

		if( freeDao.commentsinsert(conn, comment) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}		
		
		return commentno;
	}

	@Override
	public FreeComments getCommentno(HttpServletRequest req) {

		FreeComments comment = new FreeComments();
		Connection conn = JDBCTemplate.getConnection();
		
		String param = req.getParameter("commentNo");
		if(param!=null && !"".equals(param)) {
			
			comment.setCommentNo(Integer.parseInt(param));
		}
		
		comment = freeDao.selectcommentBycommentno(conn, comment);
		
		//결과 객체 반환
		return comment;
	}
	
	@Override
	public FreeComments getUpdateComment(HttpServletRequest req) {
		
		FreeComments comment = new FreeComments();
		
		String param = req.getParameter("commentNo");
		if(param!=null && !"".equals(param)) {
			comment.setCommentNo(Integer.parseInt(param));
		}

		comment.setCommentContent( req.getParameter("commentContent") );
		
		//결과 객체 반환
		return comment;
	}
	
	@Override
	public FreeComments getComment(int commentno) {

		FreeComments comment = new FreeComments();
		Connection conn = JDBCTemplate.getConnection();
		
		comment.setCommentNo(commentno);
		comment = freeDao.selectcommentBycommentno(conn, comment);
		
		//결과 객체 반환
		return comment;
	}
	
	@Override
	public void commentUpdate(HttpServletRequest req) {
		FreeComments comment = new FreeComments();
		Connection conn = JDBCTemplate.getConnection();
		
		//commentNo 추가
		comment.setCommentNo(Integer.parseInt(req.getParameter("commentNo")));
		comment.setCommentContent( req.getParameter("commentContent") );

		
		System.out.println("Comment Update : " + comment);
		if( freeDao.commentsupdate(conn, comment) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}	
	}

	@Override
	public void commentDelete(FreeComments comment) {
		Connection conn = JDBCTemplate.getConnection();
		
		if( freeDao.commentsdelete(conn, comment) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}
	
}

	