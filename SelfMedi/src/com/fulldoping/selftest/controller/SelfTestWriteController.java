package com.fulldoping.selftest.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.selftest.service.face.SelfTestService;
import com.fulldoping.selftest.service.impl.SelfTestServiceImpl;

@WebServlet("/selftest/write")
public class SelfTestWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SelfTestService selfTestService = new SelfTestServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//테스트코드 - 추후 주석처리
		System.out.println("/selftest/write [GET]");
		
		//자가진단작성 페이지로 이동
		req.getRequestDispatcher("/WEB-INF/views/selftest/write.jsp").forward(req, resp);
		
	} //end doGet
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//테스트코드 - 추후 주석처리
		System.out.println("/selftest/write [POST]");
		
		//form태그로 받은 정보들을 DB에 삽입하는 메소드
		selfTestService.insert(req);
		
		//결과 목록 조회 서블릿으로 이동 - URL이 변한다
		resp.sendRedirect("/selftest/list");
		
	} //end doPost

} //end Servlet
