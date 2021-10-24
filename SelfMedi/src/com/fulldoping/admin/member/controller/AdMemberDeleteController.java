package com.fulldoping.admin.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.admin.member.service.face.AdMemberService;
import com.fulldoping.admin.member.service.impl.AdMemberServiceImpl;
import com.fulldoping.member.dto.Member;

@WebServlet("/ad/member/delete")
public class AdMemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//MemberService 객체 생성
	private AdMemberService admemberService = new AdMemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[GET]");
		
		Member member = admemberService.getUserNo(req);
		
		admemberService.delete(member);
		
		resp.sendRedirect("/ad/member/list");
		
	}
	
}
