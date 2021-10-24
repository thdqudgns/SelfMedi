package com.fulldoping.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.member.dto.Member;
import com.fulldoping.member.service.face.MemberService;
import com.fulldoping.member.service.impl.MemberServiceImpl;

@WebServlet("/member/join")
public class MemberJoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//MemberService 객체 생성
	MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/join [GET]");
		
		// VIEW 지정 - forward
		req.getRequestDispatcher("/WEB-INF/views/member/join.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/join [POST]");
		
		// 요청파라미터 처리
		Member param = memberService.getJoinMember(req);
		
		// 회원가입
		memberService.join(param);
		
		// 메인으로 리다이렉션
		resp.sendRedirect("/main");
	}	
	
}
