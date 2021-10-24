package com.fulldoping.admin.qnadeclare.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fulldoping.QnA.dto.QnADeclare;
import com.fulldoping.QnA.dto.QnAFile;
import com.fulldoping.admin.qnadeclare.dao.face.AdQnADeclareDao;
import com.fulldoping.admin.qnadeclare.dao.impl.AdQnADeclareDaoImpl;
import com.fulldoping.admin.qnadeclare.paging.QnADeclarePaging;
import com.fulldoping.admin.qnadeclare.service.face.AdQnADeclareService;
import com.fulldoping.common.JDBCTemplate;

public class AdQnADeclareServiceImpl implements AdQnADeclareService{

	//AdQnADeclareDao 객체 생성
	private AdQnADeclareDao adQnaDeclareDao = new AdQnADeclareDaoImpl();
	
	@Override
	public List<QnADeclare> getList(QnADeclarePaging qnaDeclarePaging) {
		//게시글 전체 조회 결과 처리 - 페이징 추가
		return adQnaDeclareDao.selectAll(JDBCTemplate.getConnection(), qnaDeclarePaging);
	}
	
	@Override
	public QnADeclarePaging getPaging(HttpServletRequest req) {
		
		//전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}
		
		//QnA 테이블의 총 게시글 수를 조회한다
		int totalCount = adQnaDeclareDao.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging객체 생성
		QnADeclarePaging qnaDeclarePaging = new QnADeclarePaging(totalCount, curPage);
		
		return qnaDeclarePaging;
	}

	@Override
	public QnADeclare getboardNo(HttpServletRequest req) {
		
		//boardNo를 저장할 객체 생성
		QnADeclare boardNo = new QnADeclare();	
		
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
	public QnADeclare view(QnADeclare boardNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		//조회수 증가
		if( adQnaDeclareDao.updateHit(conn, boardNo) == 1 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//게시글 조회
		QnADeclare qnaDeclare = adQnaDeclareDao.selectQnAByBoardNo(conn, boardNo); 
			
		return qnaDeclare;
	}
	
	@Override
	public String getuserNick(QnADeclare viewQnaDeclare) {
		return adQnaDeclareDao.selectuserNickByUserId(JDBCTemplate.getConnection(), viewQnaDeclare);
	}
	
	@Override
	public QnAFile viewFile(QnADeclare viewQnaDeclare) {
		return adQnaDeclareDao.selectFile(JDBCTemplate.getConnection(), viewQnaDeclare);
	}
	
	@Override
	public void delete(QnADeclare qnaDeclare) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		if( adQnaDeclareDao.deleteFile(conn, qnaDeclare) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		if( adQnaDeclareDao.deletedeclare(conn, qnaDeclare) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		} 
		
		if( adQnaDeclareDao.deletecomment(conn, qnaDeclare) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		} 

		if( adQnaDeclareDao.deleteqna(conn, qnaDeclare) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}




















