package com.fulldoping.admin.member.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fulldoping.admin.member.dao.face.AdMemberDao;
import com.fulldoping.admin.member.dao.impl.AdMemberDaoImpl;
import com.fulldoping.admin.member.paging.MemberPaging;
import com.fulldoping.admin.member.service.face.AdMemberService;
import com.fulldoping.common.JDBCTemplate;
import com.fulldoping.member.dto.Member;

public class AdMemberServiceImpl implements AdMemberService {
	
	private AdMemberDao admemberDao = new AdMemberDaoImpl();
	
	@Override
	public List<Member> getList(MemberPaging paging) {

		//회원 전체 조회 결과 처리 - 페이징 추가
		return admemberDao.selectAll(JDBCTemplate.getConnection(), paging);
	}

	@Override
	public MemberPaging getPaging(HttpServletRequest req) {
		
		//전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}

		//Board 테이블의 총 게시글 수를 조회한다
		int totalCount = admemberDao.selectCntAll(JDBCTemplate.getConnection());

		//Paging객체 생성
		MemberPaging paging = new MemberPaging(totalCount, curPage);

		return paging;
	}

	@Override
	public Member getUserNo(HttpServletRequest req) {
		//userNo를 저장할 객체 생성
		Member userNo = new Member();

		//userNo 전달파라미터 검증 - null, ""
		String param = req.getParameter("userNo");
		if(param!=null && !"".equals(param)) {

			//userNo 전달파라미터 추출
			userNo.setUserNo( Integer.parseInt(param) );
		}

		//결과 객체 반환
		return userNo;
	}

	@Override
	public Member view(Member userNo) {
		
		Connection conn = JDBCTemplate.getConnection();

//		//조회수 증가
//		if( boardDao.updateHit(conn, boardno) == 1 ) {
//			JDBCTemplate.commit(conn);
//		} else {
//			JDBCTemplate.rollback(conn);
//		}
		
		//게시글 조회
		Member member = admemberDao.selectMemberUserNo(conn, userNo); 
		
		return member;
		
	}

	@Override
	public void delete(Member member) {
		
		System.out.println("memberDao.delete(conn, member)");
		
		Connection conn = JDBCTemplate.getConnection();
		
		if( admemberDao.deleteFile(conn, member) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		if( admemberDao.delete(conn, member) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}
	

//	@Override
//	public void getOverlap(HttpServletRequest req) {
//		
//		Connection conn = JDBCTemplate.getConnection();
//		if(req.getParameter("check") == "id") {
////			memberDao.overlap();
//		}else if(req.getParameter("check") == "nick") {
//			
//		}
//		
//	}
}
