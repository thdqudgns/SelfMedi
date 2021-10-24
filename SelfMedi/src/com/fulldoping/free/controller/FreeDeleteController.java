package com.fulldoping.free.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.free.dto.Free;
import com.fulldoping.free.service.face.FreeService;
import com.fulldoping.free.service.impl.FreeServiceImpl;

@WebServlet("/free/delete")
public class FreeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스 객체 생성
	private FreeService freeService = new FreeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Free free = freeService.getboardNo(req);
		
		System.out.println("TEST DELETE Free : " + free);
		
		freeService.delete(free);
		
		//목록으로 리다이렉트
		resp.sendRedirect("/free/list");	
		
	}
	
}
