package com.fulldoping.free.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.free.service.face.FreeService;
import com.fulldoping.free.service.impl.FreeServiceImpl;


@WebServlet("/free/write")
public class FreeWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스 객체 생성
	private FreeService freeService = new FreeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//로그인 되어있지 않으면 리다이렉트
		if( req.getSession().getAttribute("login")== null || !(boolean)req.getSession().getAttribute("login") ) {
			
			resp.sendRedirect("/");
			
			return;
		}
		
		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/freeboard/write.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//작성글 삽입
		freeService.write(req);
		
		//목록으로 리다이렉트
		resp.sendRedirect("/free/list");
		
		
	}
	
}
