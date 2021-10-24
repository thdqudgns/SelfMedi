package com.fulldoping.admin.qna.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fulldoping.QnA.dto.QnA;
import com.fulldoping.QnA.dto.QnAFile;
import com.fulldoping.admin.qna.dao.face.AdQnaDao;
import com.fulldoping.admin.qna.dao.impl.AdQnaDaoImpl;
import com.fulldoping.admin.qna.paging.AdQnaPaging;
import com.fulldoping.admin.qna.service.face.AdQnaService;
import com.fulldoping.common.JDBCTemplate;

public class AdQnaServiceImpl implements AdQnaService {

	//AdFreeDao 객체생성
	private AdQnaDao adQnaDao = new AdQnaDaoImpl();
	
	@Override
	public List<QnA> getList(AdQnaPaging adQnaPaging) {
		//게시글 전체 조회 결과 처리 - 페이징 추가
		return adQnaDao.selectAll(JDBCTemplate.getConnection(), adQnaPaging);
	}
	
	@Override
	public AdQnaPaging getPaging(HttpServletRequest req) {
		
		//전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}
		
		//Free 테이블의 총 게시글 수를 조회한다
		int totalCount = adQnaDao.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging객체 생성
		AdQnaPaging getPaging = new AdQnaPaging(totalCount, curPage);
		
		return getPaging;
	}

	@Override
	public QnA getboardNo(HttpServletRequest req) {
		
		//boardNo를 저장할 객체 생성
		QnA boardNo = new QnA();	
		
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
	public QnA view(QnA boardNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		//조회수 증가
		if( adQnaDao.updateHit(conn, boardNo) == 1 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//게시글 조회
		QnA adQna = adQnaDao.selectFreeByBoardNo(conn, boardNo); 
			
		return adQna;
	}
	@Override
	public QnAFile viewFile(QnA viewQna) {
		return adQnaDao.selectFile(JDBCTemplate.getConnection(), viewQna);
	}
	
	@Override
	public void delete(QnA qna) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		if( adQnaDao.commentsdelete(conn, qna) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		if( adQnaDao.deletedeclare(conn, qna) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		if( adQnaDao.deleteFile(conn, qna) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		if( adQnaDao.delete(conn, qna) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}



