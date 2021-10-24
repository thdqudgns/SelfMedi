package com.fulldoping.selftest.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.selftest.dto.SelfTest;
import com.fulldoping.selftest.service.face.SelfTestService;
import com.fulldoping.selftest.service.impl.SelfTestServiceImpl;

@WebServlet("/selftest/delete")
public class SelfTestDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SelfTestService selfTestService = new SelfTestServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//테스트코드 - 추후 주석처리
//		System.out.println("/selftest/delete [GET]");
		
		//클라이언트의 동작으로 부터 진단서 번호 전달파라미터 얻기 -> DTO타입으로 저장
		SelfTest selftestno = selfTestService.getTestno(req);
		
		//테스트코드 - 추후 주석처리
		System.out.println("자가진단 게시판 번호 : " +  selftestno);
		
		//DB의 데이터를 삭제하는 코드
		selfTestService.delete(selftestno);
		
		//result 페이지에서 다시 list 페이지로 새로고침 한다.
		resp.sendRedirect("/selftest/list");
		
	} // end doGet

} //end servlet
