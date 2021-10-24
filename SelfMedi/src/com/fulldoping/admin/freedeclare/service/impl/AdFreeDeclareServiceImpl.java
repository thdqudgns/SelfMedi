package com.fulldoping.admin.freedeclare.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fulldoping.admin.freedeclare.dao.face.AdFreeDeclareDao;
import com.fulldoping.admin.freedeclare.dao.impl.AdFreeDeclareDaoImpl;
import com.fulldoping.admin.freedeclare.paging.FreeDeclarePaging;
import com.fulldoping.admin.freedeclare.service.face.AdFreeDeclareService;
import com.fulldoping.common.JDBCTemplate;
import com.fulldoping.free.dto.FreeDeclare;
import com.fulldoping.free.dto.FreeFile;

public class AdFreeDeclareServiceImpl implements AdFreeDeclareService{

	//AdFreeDeclareDao 객체 생성
	private AdFreeDeclareDao adFreeDeclareDao = new AdFreeDeclareDaoImpl();
	
	@Override
	public List<FreeDeclare> getList(FreeDeclarePaging freeDeclarePaging) {
		
		//게시글 전체 조회 결과 처리 - 페이징 추가
		return adFreeDeclareDao.selectAll(JDBCTemplate.getConnection(), freeDeclarePaging);
	}
	
	@Override
	public FreeDeclarePaging getPaging(HttpServletRequest req) {
		
		//전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}
		
		//Free 테이블의 총 게시글 수를 조회한다
		int totalCount = adFreeDeclareDao.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging객체 생성
		FreeDeclarePaging freeDeclarePaging = new FreeDeclarePaging(totalCount, curPage);
		
		return freeDeclarePaging;
	}
	
	@Override
	public FreeDeclare getboardNo(HttpServletRequest req) {
		
		//boardNo를 저장할 객체 생성
		FreeDeclare boardNo = new FreeDeclare();	
		
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
	public FreeDeclare view(FreeDeclare boardNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		//조회수 증가
		if( adFreeDeclareDao.updateHit(conn, boardNo) == 1 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//게시글 조회
		FreeDeclare freeDeclare = adFreeDeclareDao.selectFreeByBoardNo(conn, boardNo); 
			
		return freeDeclare;
	}
	
//	@Override
//	public String getuserNick(FreeDeclare viewFreeDeclare) {
//		return adFreeDeclareDao.selectuserNickByUserId(JDBCTemplate.getConnection(), viewFreeDeclare);
//	}
	
	@Override
	public FreeFile viewFile(FreeDeclare viewFreeDeclare) {
		return adFreeDeclareDao.selectFile(JDBCTemplate.getConnection(), viewFreeDeclare);
	}
	
	@Override
	public void delete(FreeDeclare freeDeclare) {
		
		
		
		Connection conn = JDBCTemplate.getConnection();
		
		if( adFreeDeclareDao.deleteFile(conn, freeDeclare) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		if( adFreeDeclareDao.delete(conn, freeDeclare) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		} 
		
		if( adFreeDeclareDao.deletecomments(conn, freeDeclare) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		} 
		
		if( adFreeDeclareDao.deletefree(conn, freeDeclare) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		} 
		

		
	}

}
