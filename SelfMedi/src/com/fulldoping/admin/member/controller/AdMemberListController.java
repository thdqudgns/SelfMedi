package com.fulldoping.admin.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.admin.member.service.face.AdMemberService;
import com.fulldoping.admin.member.service.impl.AdMemberServiceImpl;
import com.fulldoping.member.dto.Member;
import com.fulldoping.admin.member.paging.MemberPaging;



@WebServlet("/ad/member/list")
public class AdMemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//MemberService 객체 생성
	private AdMemberService admemberService = new AdMemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//요청파라미터를 전달하여 Paging객체 생성하기
		MemberPaging paging = admemberService.getPaging(req);
		System.out.println("[TEST] /ad/member/list [GET]");
		
		List<Member> memberList = admemberService.getList(paging);
		
		//조회결과 MODEL값 전달
		req.setAttribute("memberList", memberList);
		
		System.out.println("memberList" + memberList);
		
		//페이징 정보 MODEL값 전달
		req.setAttribute("paging", paging);
		
		req.getRequestDispatcher("/WEB-INF/views/admin/member/memberlist.jsp").forward(req, resp);
	}

}
