package com.fulldoping.admin.free.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fulldoping.admin.free.dao.face.AdFreeDao;
import com.fulldoping.admin.free.dao.impl.AdFreeDaoImpl;
import com.fulldoping.admin.free.paging.AdFreePaging;
import com.fulldoping.admin.free.service.face.AdFreeService;
import com.fulldoping.common.JDBCTemplate;
import com.fulldoping.free.dto.Free;
import com.fulldoping.free.dto.FreeFile;

public class AdFreeServiceImpl implements AdFreeService {

	//AdFreeDao 객체생성
	private AdFreeDao adFreeDao = new AdFreeDaoImpl();
	
	
	@Override
	public List<Free> getList(AdFreePaging adFreePaging) {
		//게시글 전체 조회 결과 처리 - 페이징 추가
		return adFreeDao.selectAll(JDBCTemplate.getConnection(), adFreePaging);
	}
	
	@Override
	public AdFreePaging getPaging(HttpServletRequest req) {
		//전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}
		
		//Free 테이블의 총 게시글 수를 조회한다
		int totalCount = adFreeDao.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging객체 생성
		AdFreePaging adFreePaging = new AdFreePaging(totalCount, curPage);
		
		return adFreePaging;	}

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
		if( adFreeDao.updateHit(conn, boardNo) == 1 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//게시글 조회
		Free free = adFreeDao.selectFreeByBoardNo(conn, boardNo); 
			
		return free;
	}

	@Override
	public FreeFile viewFile(Free viewFree) {
		return adFreeDao.selectFile(JDBCTemplate.getConnection(), viewFree);
	}

	@Override
	public String getuserNick(Free viewFree) {
		return adFreeDao.selectuserNickByUserId(JDBCTemplate.getConnection(), viewFree);
	}
	
	@Override
	public void delete(Free free) {
		// TODO Auto-generated method stub
		
	}
}
