package com.fulldoping.free.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.free.dto.Free;
import com.fulldoping.free.paging.FreePaging;
import com.fulldoping.free.service.face.FreeService;
import com.fulldoping.free.service.impl.FreeServiceImpl;

@WebServlet("/free/list")
public class FreeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//서비스 객체 생성
	private FreeService freeService = new FreeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청 파라미터를 전달하여 Paging객체 생성하기
		FreePaging paging = freeService.getPaging(req);
		
		//게시글 전체 조회
		List<Free> freeList = freeService.getList(paging);
		
		//조회 결과 MODEL값 전달
		req.setAttribute("freeList", freeList);
		
		//페이징 정보 MODEL값 전달
		req.setAttribute("paging", paging);
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/freeboard/list.jsp").forward(req, resp);
	}
	

	
}
