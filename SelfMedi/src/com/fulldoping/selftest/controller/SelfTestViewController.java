package com.fulldoping.selftest.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.selftest.dto.SelfTest;
import com.fulldoping.selftest.service.face.SelfTestService;
import com.fulldoping.selftest.service.impl.SelfTestServiceImpl;

@WebServlet("/selftest/view")
public class SelfTestViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SelfTestService selfTestService = new SelfTestServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//테스트코드 - 추후 주석처리
		System.out.println("/selftest/view [GET]");
		
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter out = resp.getWriter();
		//로그인 되어있지 않으면 리다이렉트 
		if( req.getSession().getAttribute("login") == null
				|| !(boolean)req.getSession().getAttribute("login") ) {
			out.println("로그인이 필요합니다.");
			req.getRequestDispatcher("/WEB-INF/views/notlogin.jsp").forward(req, resp);
//			resp.sendRedirect("/");

			return;
		}
		
		//클라이언트의 동작으로 부터 진단서 번호 전달파라미터 얻기 -> DTO타입으로 저장
		SelfTest selftestno = selfTestService.getTestno(req);
		
		//상세보기 실시
		SelfTest test = selfTestService.view(selftestno);
		
		//테스트 - 추후 주석
		System.out.println("view() 이후 저장된 정보 확인" + test);
		
		//DB에 삽입된 데이터들을 req영역에 저장하는 코드
		req.setAttribute("testInfo", test);
		
		//결과 상세 페이지로 이동
		req.getRequestDispatcher("/WEB-INF/views/selftest/view.jsp").forward(req, resp);
		
	}

}
