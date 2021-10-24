package com.fulldoping.selftest.controller;

import java.io.IOException;
import java.io.PrintWriter;
//import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fulldoping.selftest.dto.SelfTest;
import com.fulldoping.selftest.service.face.SelfTestService;
import com.fulldoping.selftest.service.impl.SelfTestServiceImpl;

@WebServlet("/selftest/list")
public class SelfTestListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SelfTestService selfTestService = new SelfTestServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//테스트코드 - 추후 주석처리
		System.out.println("/selftest/list [GET]");
		
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
		
		// 세션 영역에 있는 userNo를 가져온다.
		HttpSession session = req.getSession();

		int userno = (int) session.getAttribute("userNo");

		// 테스트코드 - 추후 주석처리
		System.out.println("사용자 번호 테스트 : " + userno);

		// 자가진단데이터 전체 조회
		List<SelfTest> list = selfTestService.getList(userno);

		// 테스트코드 - 추후 주석처리
		for (SelfTest s : list)
			System.out.println(s);

		// 조회결과 값 view에 전달
		req.setAttribute("myList", list);

		// 결과 조회 페이지로 이동
		req.getRequestDispatcher("/WEB-INF/views/selftest/list.jsp").forward(req, resp);

	} //end doGet

} //end Servlet
