package com.fulldoping.free.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.free.dto.Free;
import com.fulldoping.free.dto.FreeComments;
import com.fulldoping.free.dto.FreeFile;
import com.fulldoping.free.service.face.FreeService;
import com.fulldoping.free.service.impl.FreeServiceImpl;

@WebServlet("/free/view")
public class FreeViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스 객체 생성
	private FreeService freeService = new FreeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("view [GET]");
		
		//전달파라미터 얻기 - boardNo
		Free boardNo = freeService.getboardNo(req);
		
		//상세보기 결과 조회
		Free viewFree = freeService.view(boardNo);
		
		//조회결과 MODEL값 전달
		req.setAttribute("viewFree", viewFree);
		
		//닉네임 전달
		req.setAttribute("userNick", freeService.getuserNick(viewFree));
		
		//첨부파일 정보 조회
		FreeFile freeFile = freeService.viewFile(viewFree);
		
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("freeFile", freeFile);
		
		//댓글 전체 조회
		List<FreeComments> commentList = freeService.getCommentList(boardNo.getBoardNo());
		
		//조회결과 MODEL값 전달
		req.setAttribute("commentList", commentList);
				
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/freeboard/view.jsp").forward(req, resp);		
	}
	
	
}
