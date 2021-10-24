package com.fulldoping.QnA.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.QnA.service.face.QnAService;
import com.fulldoping.QnA.service.impl.QnAServiceImpl;

@WebServlet("/qna/write")
public class QnAWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//QnAService 객체 생성
    private QnAService qnaService = new QnAServiceImpl();   
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//로그인 되어있지 않으면 리다이렉트 
		if( req.getSession().getAttribute("login") == null
				|| !(boolean)req.getSession().getAttribute("login") ) {
			
			resp.sendRedirect("/");
			
			return;
		}
		
		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/qnaboard/write.jsp").forward(req, resp);		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//작성글 삽입
		qnaService.insert(req);
		
		//목록으로 리다이렉션
		resp.sendRedirect("/qna/list");
	}

}
