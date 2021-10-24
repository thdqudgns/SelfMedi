package com.fulldoping.admin.qna.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.QnA.dto.QnA;
import com.fulldoping.admin.qna.paging.AdQnaPaging;
import com.fulldoping.admin.qna.service.face.AdQnaService;
import com.fulldoping.admin.qna.service.impl.AdQnaServiceImpl;

@WebServlet("/ad/qna/list")
public class AdQnaListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//FreeService 객체생성
	private AdQnaService adQnaService = new AdQnaServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("[TEST] /ad/free/list [GET]");
		
		//요청파라미터를 전달하여 Paging객체 생성하기
		AdQnaPaging adQnaPaging = adQnaService.getPaging(req);		
		//게시글 전체 조회
		List<QnA> adQnaList = adQnaService.getList(adQnaPaging);
		
		//조회결과 MODEL값 전달
		req.setAttribute("adQnaList", adQnaList);
	
		//페이징 정보 MODEL값 전달
		req.setAttribute("paging", adQnaPaging);
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/admin/qna/list.jsp").forward(req, resp);		
		
	}
}















