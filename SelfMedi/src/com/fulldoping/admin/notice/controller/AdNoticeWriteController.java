package com.fulldoping.admin.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.admin.notice.service.face.AdNoticeService;
import com.fulldoping.admin.notice.service.impl.AdNoticeServiceImpl;

@WebServlet("/ad/notice/write")
public class AdNoticeWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdNoticeService adNoticeService = new AdNoticeServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		//로그인 되어있지 않으면 리다이렉트
		if( req.getSession().getAttribute("login")== null || !(boolean)req.getSession().getAttribute("login") ) {
			
			resp.sendRedirect("/");
			
			return;
		}
		
		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/admin/notice/write.jsp").forward(req, resp);
		
		
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//작성글 삽입
		adNoticeService.write(req);
		
		//목록으로 리다이렉트
		resp.sendRedirect("/ad/notice/list");
		
		
	}
	
	
}
