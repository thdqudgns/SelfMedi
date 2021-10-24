package com.fulldoping.admin.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.QnA.dto.QnA;
import com.fulldoping.admin.qna.service.face.AdQnaService;
import com.fulldoping.admin.qna.service.impl.AdQnaServiceImpl;

@WebServlet("/ad/qna/delete")
public class AdQnaDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//AdFreeService 객체생성
	private AdQnaService adQnaService = new AdQnaServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		QnA qna = adQnaService.getboardNo(req);
				
		adQnaService.delete(qna);
		
		//목록으로 리다이렉트
		resp.sendRedirect("/ad/qna/list");	
	}

}
