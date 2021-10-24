package com.fulldoping.admin.qnadeclare.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.QnA.dto.QnADeclare;
import com.fulldoping.admin.qnadeclare.service.face.AdQnADeclareService;
import com.fulldoping.admin.qnadeclare.service.impl.AdQnADeclareServiceImpl;



@WebServlet("/ad/qnaclare/delete")
public class AdQnADeclareDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스 객체 생성
	private AdQnADeclareService adQnaDeclareService = new AdQnADeclareServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		QnADeclare qnaDeclare = adQnaDeclareService.getboardNo(req);
		
		adQnaDeclareService.delete(qnaDeclare);
		
		//목록으로 리다이렉트
		resp.sendRedirect("/ad/qnaeclare/list");	
		
	}
}
