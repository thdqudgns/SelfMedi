package com.fulldoping.QnA.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.QnA.dto.QnA;
import com.fulldoping.QnA.dto.QnAFile;
import com.fulldoping.QnA.service.face.QnAService;
import com.fulldoping.QnA.service.impl.QnAServiceImpl;
import com.fulldoping.free.dto.Free;
import com.fulldoping.free.dto.FreeFile;


@WebServlet("/qna/declare")
public class QnADeclareListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스 객체 생성
	private QnAService qnaService = new QnAServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//전달파라미터 얻기 - boardNo
		QnA boardNo = qnaService.getBoardno(req); 
		
		//상세보기 결과 조회
		QnA declareQnA = qnaService.getview(boardNo);
		
		//닉네임 전달
		req.setAttribute("userNick", qnaService.getNick(declareQnA));
		
		//조회결과 MODEL값 전달
		req.setAttribute("declareQnA", declareQnA); // 데이터 넘어 오는거 확인
		
		//첨부파일 정보 VIEW에 전달
		QnAFile qnaFile = qnaService.getviewFile(declareQnA);  // 넘어 오는거 확인 환료
		
		req.setAttribute("qnaFile", qnaFile); // 넘어 오는거 확인
				
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/qnaboard/qnadeclare.jsp").forward(req, resp);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		qnaService.declare(req);
		
		resp.sendRedirect("/qna/list");
		
		
	}
	
}
