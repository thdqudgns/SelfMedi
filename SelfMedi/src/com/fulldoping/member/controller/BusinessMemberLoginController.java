package com.fulldoping.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fulldoping.member.dto.Member;
import com.fulldoping.member.service.face.MemberService;
import com.fulldoping.member.service.impl.MemberServiceImpl;

@WebServlet("/member/businesslogin")
public class BusinessMemberLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//멤버 서비스 객체 생성
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/businesslogin [GET]");
		
		//VIEW 지정 - forward
		req.getRequestDispatcher("/WEB-INF/views/member/businesslogin.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	      //전달파라미터 얻기 - 로그인 정보
	      Member member = memberService.getLoginMember(req);
	      
	      //로그인 인증
	      boolean login = memberService.login(member);

	      if( login ) {
	    	  //로그인 사용자의 정보 얻어오기
	    	  member = memberService.info(member);
	    	  
	    	  //세션정보 저장하기
	    	  HttpSession session = req.getSession();
	    	  session.setAttribute("login", login);
	    	  session.setAttribute("userNo", member.getUserNo());
	    	  session.setAttribute("userKind", member.getUserKind());
	    	  session.setAttribute("userNick", member.getUserNick());

	    	  req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
	    	  return;
	      } else {
	    	  req.getRequestDispatcher("/WEB-INF/views/loginfail.jsp").forward(req, resp);
	    	  return;
	      }
	      
	}
}
