package com.fulldoping.admin.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.QnA.dto.QnA;
import com.fulldoping.QnA.dto.QnAFile;
import com.fulldoping.admin.qna.service.face.AdQnaService;
import com.fulldoping.admin.qna.service.impl.AdQnaServiceImpl;

@WebServlet("/ad/qna/view")
public class AdQnaViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//FreeService 객체생성
	private AdQnaService adQnaService = new AdQnaServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		System.out.println("view [GET]");
		
		//전달파라미터 얻기 - boardNo
		QnA boardNo = adQnaService.getboardNo(req);
		
		//상세보기 결과 조회
		QnA viewQna = adQnaService.view(boardNo);
		
		//조회결과 MODEL값 전달
		req.setAttribute("viewQna", viewQna);
		
		//닉네임 전달
//		req.setAttribute("userNick", adQnaService.getuserNick(viewQna));
		
		//첨부파일 정보 조회
		QnAFile qnaFile = adQnaService.viewFile(viewQna);
		
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("qnaFile", qnaFile);
			
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/admin/qna/view.jsp").forward(req, resp);	
		
	}
}
