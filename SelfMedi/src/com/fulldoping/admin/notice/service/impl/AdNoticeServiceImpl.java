package com.fulldoping.admin.notice.service.impl;

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

import com.fulldoping.admin.notice.dao.face.AdNoticeDao;
import com.fulldoping.admin.notice.dao.impl.AdNoticeDaoImpl;
import com.fulldoping.admin.notice.paging.AdNoticePaging;
import com.fulldoping.admin.notice.service.face.AdNoticeService;
import com.fulldoping.common.JDBCTemplate;
import com.fulldoping.notice.dto.Notice;
import com.fulldoping.notice.dto.NoticeFile;

public class AdNoticeServiceImpl implements AdNoticeService{

	//AdNoticeDao 객체 생성
	private AdNoticeDao adNoticeDao = new AdNoticeDaoImpl();
	
	@Override
	public List<Notice> getList(AdNoticePaging adNoticePaging) {
		//게시글 전체 조회 결과 처리 - 페이징 추가
		return adNoticeDao.selectAll(JDBCTemplate.getConnection(), adNoticePaging);
	}
	
	@Override
	public AdNoticePaging getPaging(HttpServletRequest req) {
		
		//전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}
		
		//Free 테이블의 총 게시글 수를 조회한다
		int totalCount = adNoticeDao.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging객체 생성
		AdNoticePaging adNoticePaging = new AdNoticePaging(totalCount, curPage);
		
		return adNoticePaging;
	}	
		
	@Override
	public Notice getboardNo(HttpServletRequest req) {
		
		//boardNo를 저장할 객체 생성
		Notice boardNo = new Notice();	
		
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
	public Notice view(Notice boardNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		//조회수 증가
		if( adNoticeDao.updateHit(conn, boardNo) == 1 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//게시글 조회
		Notice notice = adNoticeDao.selectFreeByBoardNo(conn, boardNo); 
			
		return notice;
	}

	@Override
	public String getuserNick(Notice viewNotice) {
		return adNoticeDao.selectuserNickByUserId(JDBCTemplate.getConnection(), viewNotice);
	}
	@Override
	public NoticeFile viewFile(Notice viewNotice) {
		return adNoticeDao.selectFile(JDBCTemplate.getConnection(), viewNotice);
	}
	
	@Override
	public void write(HttpServletRequest req) {
		
		//게시글 정보 DTO 객체
		Notice notice = null;
		
		//첨부파일 정보 DTO 객체
		NoticeFile noticeFile = null;
		
		//파일업로드 형태의 데이터가 맞는지 검사
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if( !isMultipart ) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");
			
			return; //write() 메소드 중단
		}
		
		//게시글 정보를 저장할 DTO객체 생성
		notice = new Notice();
		
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
					notice.setBoardTitle( value );
				} else if( "content".equals(key) ) {
					notice.setBoardContent( value );
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
				noticeFile = new NoticeFile();
				noticeFile.setOriginName(origin);
				noticeFile.setStoredName(stored);
				noticeFile.setFileSize( (int)item.getSize() );
				
			} //if( !item.isFormField() ) end
		} //while( iter.hasNext() ) end
			
		//DB연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 번호 생성 - DAO 이용
		int boardNo = adNoticeDao.selectNextBoardNo(conn);
		
		//게시글 정보가 있을 경우
		if(notice != null) {
			
			//작성자 userId 입력
//			free.setUserId( (String)req.getSession().getAttribute("userid") );
			
			notice.setUserId( adNoticeDao.getUserId(conn, (Integer)req.getSession().getAttribute("userNo")) );
			
//			//작성자 userNo 입력
			notice.setUserNo( (int) req.getSession().getAttribute("userNo") ); //userNO (PK)
			
			notice.setUserNick( (String) req.getSession().getAttribute("userNick") ); 
			
			//게시글 번호입력
			notice.setBoardNo(boardNo); 
			
			if(notice.getBoardTitle()==null || "".equals(notice.getBoardTitle())) {
				notice.setBoardTitle("(제목없음)");
			}
			
			if( adNoticeDao.insert(conn, notice) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		//첨부파일 정보가 있을 경우
		if(noticeFile != null) {
			noticeFile.setBoardNo(boardNo); //게시글 번호 입력
			
			if( adNoticeDao.insertFile(conn, noticeFile) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
	}
	
	
	@Override
	public void update(HttpServletRequest req) {
		//게시글 정보 DTO 객체
		Notice notice = null;
		
		//첨부파일 정보 DTO 객체
		NoticeFile noticeFile = null;
		
		//파일업로드 형태의 데이터가 맞는지 검사
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
				
		if( !isMultipart ) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");
					
			return; //write() 메소드 중단
		}	
	
		//게시글 정보를 저장할 DTO객체 생성
		notice = new Notice();
		
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
					notice.setBoardNo( Integer.parseInt(value) );
				} else if( "title".equals(key) ) {
					notice.setBoardTitle( value );
				} else if( "content".equals(key) ) {
					notice.setBoardContent( value );
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
				noticeFile = new NoticeFile();
				noticeFile.setOriginName(origin);
				noticeFile.setStoredName(stored);
				noticeFile.setFileSize( (int)item.getSize() );
				
			} //if( !item.isFormField() ) end
		} //while( iter.hasNext() ) end
		
		
		
		
		//DB연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 정보가 있을 경우
		if(notice != null) {
			if( adNoticeDao.update(conn, notice) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		//첨부파일 정보가 있을 경우
		if(noticeFile != null) {
			noticeFile.setBoardNo(notice.getBoardNo()); //게시글 번호 입력 
			
			if( adNoticeDao.deleteFile(conn, notice) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
			
			if( adNoticeDao.insertFile(conn, noticeFile) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		//첨부파일 정보가 없을 경우
		if(noticeFile == null) {
			
			if( adNoticeDao.deleteFile(conn, notice) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
	}
	
	@Override
	public void delete(Notice notice) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		if( adNoticeDao.commentsdelete(conn, notice) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		if( adNoticeDao.deleteFile(conn, notice) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		if( adNoticeDao.delete(conn, notice) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}
	
}
