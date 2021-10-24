package com.fulldoping.notice.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fulldoping.common.JDBCTemplate;
import com.fulldoping.notice.dao.face.NoticeDao;
import com.fulldoping.notice.dao.impl.NoticeDaoImpl;
import com.fulldoping.notice.dto.Notice;
import com.fulldoping.notice.dto.NoticeComments;
import com.fulldoping.notice.dto.NoticeFile;
import com.fulldoping.notice.paging.NoticePaging;
import com.fulldoping.notice.service.face.NoticeService;

public class NoticeServiceImpl implements NoticeService {

	//NoticeDao 객체 생성
	private NoticeDao noticeDao = new NoticeDaoImpl();
	
	@Override
	public List<Notice> getList(NoticePaging paging) {
		
		//게시글 전체 조회 결과 처리 - 페이징 추가 
		return noticeDao.selectAll(JDBCTemplate.getConnection(), paging);
	}
	
	@Override
	public NoticePaging getPaging(HttpServletRequest req) {
		
		//전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}
		
		//Free 테이블의 총 게시글 수를 조회한다
		int totalCount = noticeDao.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging객체 생성
		NoticePaging paging = new NoticePaging(totalCount, curPage);
		
		return paging;
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
		if( noticeDao.updateHit(conn, boardNo) == 1 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//게시글 조회
		Notice notice = noticeDao.selectFreeByBoardNo(conn, boardNo); 
			
		return notice;
	}

	@Override
	public String getuserNick(Notice viewNotice) {
		return noticeDao.selectuserNickByUserId(JDBCTemplate.getConnection(), viewNotice);
	}

	@Override
	public NoticeFile viewFile(Notice viewNotice) {
		return noticeDao.selectFile(JDBCTemplate.getConnection(), viewNotice);
	}

	
	//댓글
	@Override
	public List<NoticeComments> getCommentList(int boardNo) {
		int boardnum = boardNo;
		
		//게시글 전체 조회 결과처리
		return noticeDao.selectAllComments(JDBCTemplate.getConnection(), boardnum);
	}

	@Override
	public void commentDelete(NoticeComments comment) {
		Connection conn = JDBCTemplate.getConnection();
		
		if( noticeDao.commentsdelete(conn, comment) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}

	@Override
	public void commentUpdate(HttpServletRequest req) {
		NoticeComments comment = new NoticeComments();
		Connection conn = JDBCTemplate.getConnection();
		
		//commentNo 추가
		comment.setCommentNo(Integer.parseInt(req.getParameter("commentNo")));
		comment.setCommentContent( req.getParameter("commentContent") );

		
		System.out.println("Comment Update : " + comment);
		if( noticeDao.commentsupdate(conn, comment) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

	@Override
	public int commentInsert(HttpServletRequest req) {
		NoticeComments comment = new NoticeComments();
		Connection conn = JDBCTemplate.getConnection();
		
		//commentNo 추가
		int commentno = noticeDao.selectNextCommentno(conn);
		comment.setCommentNo(commentno);
		comment.setBoardNo(Integer.parseInt(req.getParameter("boardNo")));
		comment.setUserNo((Integer)req.getSession().getAttribute("userNo"));
		comment.setUserNick(noticeDao.getUserNick(conn, (Integer)req.getSession().getAttribute("userNo")));
		comment.setCommentContent( req.getParameter("content") );

		if( noticeDao.commentsinsert(conn, comment) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}		
		
		return commentno;
	}
	
	@Override
	public NoticeComments getCommentno(HttpServletRequest req) {

		NoticeComments comment = new NoticeComments();
		Connection conn = JDBCTemplate.getConnection();
		
		String param = req.getParameter("commentNo");
		if(param!=null && !"".equals(param)) {
			
			comment.setCommentNo(Integer.parseInt(param));
		}
		
		comment = noticeDao.selectcommentBycommentno(conn, comment);
		
		//결과 객체 반환
		return comment;
	}
	
	@Override
	public NoticeComments getUpdateComment(HttpServletRequest req) {
		NoticeComments comment = new NoticeComments();
		
		String param = req.getParameter("commentNo");
		if(param!=null && !"".equals(param)) {
			comment.setCommentNo(Integer.parseInt(param));
		}

		comment.setCommentContent( req.getParameter("commentContent") );
		
		//결과 객체 반환
		return comment;
	}
	
	@Override
	public NoticeComments getComment(int commentno) {
		NoticeComments comment = new NoticeComments();
		Connection conn = JDBCTemplate.getConnection();
		
		comment.setCommentNo(commentno);
		comment = noticeDao.selectcommentBycommentno(conn, comment);
		
		//결과 객체 반환
		return comment;
	}
}
