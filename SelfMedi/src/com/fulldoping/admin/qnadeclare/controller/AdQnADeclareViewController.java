package com.fulldoping.admin.qnadeclare.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.QnA.dto.QnADeclare;
import com.fulldoping.QnA.dto.QnAFile;
import com.fulldoping.admin.qnadeclare.service.face.AdQnADeclareService;
import com.fulldoping.admin.qnadeclare.service.impl.AdQnADeclareServiceImpl;


@WebServlet("/ad/qnadeclare/view")
public class AdQnADeclareViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스 객체 생성
	private AdQnADeclareService adQnaDeclareService = new AdQnADeclareServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//전달파라미터 얻기 - boardNo
		QnADeclare boardNo = adQnaDeclareService.getboardNo(req);
		
		//상세보기 결과 조회
		QnADeclare viewQnaDeclare = adQnaDeclareService.view(boardNo);
		
		//조회결과 MODEL값 전달
		req.setAttribute("viewQnaDeclare", viewQnaDeclare);
		
		//닉네임 전달
		req.setAttribute("userNick", adQnaDeclareService.getuserNick(viewQnaDeclare));
		
		//첨부파일 정보 조회
		QnAFile qnaFile = adQnaDeclareService.viewFile(viewQnaDeclare);
		
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("qnaFile", qnaFile);

		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/admin/declare/qnaview.jsp").forward(req, resp);		
		
		
	}
		
}
	

