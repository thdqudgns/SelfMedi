package com.fulldoping.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/business/memberfile")
public class BusinessMemberFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//서비스 객체
//	private FileService fileService = new FileServiceImpl();	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/business/memberfile [GET]");
		
		//View 지정하고 포워드
		req.getRequestDispatcher("/WEB-INF/views/").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/business/memberfile [POST]");
	
//		fileService.fileupload(req, resp);
	}
}
