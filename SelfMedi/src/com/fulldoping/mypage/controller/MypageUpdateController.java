package com.fulldoping.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fulldoping.member.dto.Member;
import com.fulldoping.mypage.service.face.MypageService;
import com.fulldoping.mypage.service.impl.MypageServiceImpl;

@WebServlet("/mypage/update")
public class MypageUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MypageService mypageService = new MypageServiceImpl();
       
	 @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    	System.out.println("/mypage/update [doGet]");
	    	HttpSession session = req.getSession();
	    	
	    	if(session.getAttribute("login") != null) {
				
				//맴버 정보
				Member member = mypageService.getLoginMemberInformation(session);
				System.out.println(member.toString());
				
				//조회결과 MODEL값 전달
				req.setAttribute("member", member);

				//VIEW 지정 - forward
				req.getRequestDispatcher("/WEB-INF/views/mypage/mypageUpdate.jsp").forward(req, resp);
				
			}else {
				 //로그인 페이지로 리다이렉트
			      resp.sendRedirect("/member/login");
			}
	    }
	    
	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    	System.out.println("/mypage/update [doPost]");
	    	
	    	req.setCharacterEncoding("UTF-8");
	    	
	    	Member member = new Member();
	    	
	    	if(Integer.parseInt(req.getParameter("updateNo")) == 1) {
	    		member = mypageService.updateNick(req);
	    	
	    		req.setAttribute("member", member);
	    		//VIEW 지정 - forward
				req.getRequestDispatcher("/WEB-INF/views/mypage/mypageNick.jsp").forward(req, resp);
				
	    	}
	    	if(Integer.parseInt(req.getParameter("updateNo")) == 2) {
	    		member = mypageService.updatePw(req);
	    	
	    		req.setAttribute("member", member);
	    		//VIEW 지정 - forward
				req.getRequestDispatcher("/WEB-INF/views/mypage/mypagePw.jsp").forward(req, resp);
				
	    	}
	    	if(Integer.parseInt(req.getParameter("updateNo")) == 3) {
	    		member = mypageService.updateEm(req);
	    	
	    		req.setAttribute("member", member);
	    		//VIEW 지정 - forward
				req.getRequestDispatcher("/WEB-INF/views/mypage/mypageEm.jsp").forward(req, resp);
				
	    	}
	    	if(Integer.parseInt(req.getParameter("updateNo")) == 4) {
	    		member = mypageService.updatePh(req);
	    	
	    		req.setAttribute("member", member);
	    		//VIEW 지정 - forward
				req.getRequestDispatcher("/WEB-INF/views/mypage/mypagePh.jsp").forward(req, resp);
				
	    	}
	    }
	    

}
