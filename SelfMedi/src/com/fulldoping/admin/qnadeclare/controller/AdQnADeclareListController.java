package com.fulldoping.admin.qnadeclare.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.QnA.dto.QnADeclare;
import com.fulldoping.admin.qnadeclare.paging.QnADeclarePaging;
import com.fulldoping.admin.qnadeclare.service.face.AdQnADeclareService;
import com.fulldoping.admin.qnadeclare.service.impl.AdQnADeclareServiceImpl;

@WebServlet("/ad/qnaeclare/list")
public class AdQnADeclareListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	//서비스 객체 생성
	private AdQnADeclareService adQnaDeclareService = new AdQnADeclareServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		//요청 파라미터를 전달하여 Paging객체 생성하기
		QnADeclarePaging qnaDeclarePaging = adQnaDeclareService.getPaging(req);
		
		//게시글 전체 조회
		List<QnADeclare> qnaDeclareList = adQnaDeclareService.getList(qnaDeclarePaging);
		
		//조회 결과 MODEL값 전달
		req.setAttribute("qnaDeclareList", qnaDeclareList);
		
		//페이징 정보 MODEL값 전달
		req.setAttribute("paging", qnaDeclarePaging);
		System.out.println("TETETEWTWETEWTWETWEtew" + qnaDeclareList + qnaDeclarePaging);
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/admin/declare/qnalist.jsp").forward(req, resp);
	}
		
}


