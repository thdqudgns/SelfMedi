package com.fulldoping.mypage.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fulldoping.QnA.dto.QnA;
import com.fulldoping.common.JDBCTemplate;
import com.fulldoping.free.dto.Free;
import com.fulldoping.member.dto.Member;
import com.fulldoping.mypage.dao.face.MypageDao;
import com.fulldoping.mypage.dao.impl.MypageDaoImpl;
import com.fulldoping.mypage.service.face.MypageService;
import com.fulldoping.notice.dto.Notice;
import com.fulldoping.selftest.dto.SelfTest;

public class MypageServiceImpl implements MypageService {

	private MypageDao mypagedao = new MypageDaoImpl();
	
	@Override
	public Member getLoginMemberInformation(HttpSession session) {

		//객체 생성
		Connection conn = JDBCTemplate.getConnection();
		Member member = new Member();
		int memberNo = (int)session.getAttribute("userNo");
		
		member = mypagedao.getMemberInformation(conn, memberNo);
		
		return member;
	}
	
	@Override
	public List<SelfTest> getList(int userNo) {
		return mypagedao.getList(JDBCTemplate.getConnection(), userNo);
	}
	
	@Override
	public SelfTest getTestno(HttpServletRequest req) {
		//테스트 코드 - 추후 주석처리
		System.out.println("SelfTestServiceImpl - getTestno() 호출");
		
		//진단서 번호를 반환할 객체 생성
		SelfTest selftestno = new SelfTest();
		
		//전달 파라미터 검증
		String param = req.getParameter("selftestNo");
		if( param !=null && !"".equals(param) ) { //null이 아니고 공백도 아니라면
			//selftestno에 저장한다
			selftestno.setSelftestNo(Integer.parseInt(param));
		} else {
			System.out.println("[ERROR] 전달파라미터(selftestno) 잘못 전달됨");
		}
		
		//테스트코드 - 추후 주석처리
		System.out.println("getTestno() : " + selftestno);
		
		return selftestno;
	}
	
	@Override
	public void delete(SelfTest selftestno) {
		//테스트 코드 - 추후 주석처리
		System.out.println("SelfTestServiceImpl - view() 호출");
		
		Connection conn = JDBCTemplate.getConnection();
		
		int res = mypagedao.delete(conn, selftestno);
		
		System.out.println("res : " + res);
		
		if(res > 0) {
			JDBCTemplate.commit(conn);
			System.out.println("delete commit");
		}else {
			JDBCTemplate.rollback(conn);
			System.out.println("delete rollback");
		}
		
	}
	
	@Override
	public List<Notice> getNoticeList(HttpSession session) {
		//객체 생성
		Connection conn = JDBCTemplate.getConnection();
		int memberNo = (int)session.getAttribute("userNo");
		
		return mypagedao.getNoticeInformation(conn, memberNo);
	}

	@Override
	public List<QnA> getQnAList(HttpSession session) {
		//객체 생성
		Connection conn = JDBCTemplate.getConnection();
		int memberNo = (int)session.getAttribute("userNo");
		
		return mypagedao.getQnAInformation(conn, memberNo);
	}

	@Override
	public List<Free> getFreeList(HttpSession session) {
		//객체 생성
		Connection conn = JDBCTemplate.getConnection();
		int memberNo = (int)session.getAttribute("userNo");
		
		return mypagedao.getFreeInformation(conn, memberNo);
	}
	
	@Override
	public Member updateNick(HttpServletRequest req) {
		//객체 생성
		Connection conn = JDBCTemplate.getConnection();
		Member member = new Member();
		member.setUserNo(Integer.parseInt(req.getParameter("userNo")));
		member.setUserNick(req.getParameter("userNick"));
		
		int res= mypagedao.updateNickInformation(conn, member);
		if( res > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		member = mypagedao.getMemberInformation(conn, member.getUserNo());
		
		return member;
	}
	
	@Override
	public Member updatePw(HttpServletRequest req) {
		//객체 생성
		Connection conn = JDBCTemplate.getConnection();
		Member member = new Member();
		member.setUserNo(Integer.parseInt(req.getParameter("userNo")));
		member.setUserPw(req.getParameter("userPw"));
		
		int res = mypagedao.updatePwInformation(conn, member);
		if( res > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		member = mypagedao.getMemberInformation(conn, member.getUserNo());
		
		return member;
	}
	
	@Override
	public Member updateEm(HttpServletRequest req) {
		//객체 생성
		Connection conn = JDBCTemplate.getConnection();
		Member member = new Member();
		member.setUserNo(Integer.parseInt(req.getParameter("userNo")));
		member.setUserEm(req.getParameter("userEm"));
		
		int res = mypagedao.updateEmInformation(conn, member);
		if( res > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		member = mypagedao.getMemberInformation(conn, member.getUserNo());
		
		return member;
	}
	
	@Override
	public Member updatePh(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();
		Member member = new Member();
		member.setUserNo(Integer.parseInt(req.getParameter("userNo")));
		member.setUserPh(req.getParameter("userPh"));
		
		int res = mypagedao.updatePhInformation(conn, member);
		if( res > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		member = mypagedao.getMemberInformation(conn, member.getUserNo());
		
		return member;
	}
	
}
