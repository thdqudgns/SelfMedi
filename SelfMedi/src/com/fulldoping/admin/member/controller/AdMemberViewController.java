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


@WebServlet("/ad/member/view")
public class AdMemberViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdMemberService admemberService = new AdMemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("[GET]");
		
		//전달파라미터 얻기 - userNo
		Member userNo = admemberService.getUserNo(req);
		
//		System.out.println("[member]");
		
		//상세보기 결과 조회
		Member viewMember = admemberService.view(userNo);
		
//		System.out.println("[viewMember]");

		//조회결과 MODEL값 전달
		req.setAttribute("viewMember", viewMember);
		
//		System.out.println("[viewMember]");

		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/admin/member/memberview.jsp").forward(req, resp);
		
	}

}
