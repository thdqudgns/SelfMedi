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

@WebServlet("/member/finduserpw")
public class MemberFindUserPwController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/finduserpw [GET]");
		
		req.getRequestDispatcher("/WEB-INF/views/member/finduserpw.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/finduserpw [POST]");
		
		//인코딩
		req.setCharacterEncoding("UTF-8");
		
		//변수저장
		String userId = req.getParameter("userId");
		String userName = req.getParameter("userName");
		String userEm = req.getParameter("userEm");
		
		//비지니스로직
		Member member = memberService.findUserPw(userId, userName, userEm);
		
		if(member != null) {
			req.setAttribute("member", member);
		}
		
		
		req.getRequestDispatcher("/WEB-INF/views/member/finduserPwResult.jsp").forward(req, resp);

	}
}
