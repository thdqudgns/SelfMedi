package com.fulldoping.selftest.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fulldoping.selftest.dao.face.SelfTestDao;
import com.fulldoping.selftest.dao.impl.SelfTestDaoImpl;
import com.fulldoping.selftest.dto.SelfTest;
import com.fulldoping.selftest.service.face.SelfTestService;

import common.JDBCTemplate;

public class SelfTestServiceImpl implements SelfTestService {
	
	private SelfTestDao selftestDao = new SelfTestDaoImpl();

	@Override
	public SelfTest insert(HttpServletRequest req) {
		
		//테스트 코드 - 추후 주석처리
		System.out.println("SelfTestServiceImpl - insert() 호출");
		
		
		//데이터를 DB에 삽입한다
		//1. req에서 받은걸 DTO로 옮긴다
		
		// DTO객체 생성
		
		SelfTest selftest = new SelfTest();
		
		//selftestno는 DAO에서 시퀀스로 생성하자.
		selftest.setUserNo(Integer.parseInt(req.getParameter("userno")));
		selftest.setUserName(req.getParameter("name"));
		selftest.setUserAge(req.getParameter("age"));
		selftest.setUserGender(req.getParameter("gender"));
		selftest.setQuestion01(req.getParameter("q1"));
		selftest.setQuestion02(req.getParameter("q2"));
		selftest.setQuestion03(req.getParameter("q3"));
		selftest.setQuestion04(req.getParameter("q4"));
		selftest.setQuestion05(req.getParameter("q5"));
		selftest.setQuestion06(req.getParameter("q6"));
		selftest.setQuestion07(req.getParameter("q7"));
		
		//테스트코드 - 추후 주석처리
		System.out.println("insert메소드 실행 전 : " + selftest);
		
		if(selftest.getUserName()==null || "".equals( selftest.getUserName() )) {
			selftest.setUserName("(이름없음)");
		}
		
		Connection conn = JDBCTemplate.getConnection();
		
		if( selftestDao.insert(conn, selftest) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//테스트코드 - 추후 주석처리
		System.out.println("insert메소드 실행 후 : " + selftest);
		
		return selftest;
	}

	@Override
	public List<SelfTest> getList(int userno) {
		
		//테스트 코드 - 추후 주석처리
		System.out.println("SelfTestServiceImpl - getList() 호출");
		
		return selftestDao.getList(JDBCTemplate.getConnection(), userno);
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
	public SelfTest view(SelfTest selftestno) {
		
		//테스트 코드 - 추후 주석처리
		System.out.println("SelfTestServiceImpl - view() 호출");
		
		return selftestDao.viewByTestno(JDBCTemplate.getConnection(), selftestno);
	}

	@Override
	public void delete(SelfTest selftestno) {
		
		//테스트 코드 - 추후 주석처리
		System.out.println("SelfTestServiceImpl - view() 호출");
		
		Connection conn = JDBCTemplate.getConnection();
		
		int res = selftestDao.delete(conn, selftestno);
		
		System.out.println("res : " + res);
		
		if(res > 0) {
			JDBCTemplate.commit(conn);
			System.out.println("delete commit");
		}else {
			JDBCTemplate.rollback(conn);
			System.out.println("delete rollback");
		}
		
	}


}
