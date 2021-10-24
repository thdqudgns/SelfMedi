package com.fulldoping.admin.free.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.admin.free.paging.AdFreePaging;
import com.fulldoping.admin.free.service.face.AdFreeService;
import com.fulldoping.admin.free.service.impl.AdFreeServiceImpl;
import com.fulldoping.free.dto.Free;

@WebServlet("/ad/free/list")
public class AdFreeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//FreeService 객체생성
	private AdFreeService adFreeService = new AdFreeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("[TEST] /ad/free/list [GET]");
		
		//요청파라미터를 전달하여 Paging객체 생성하기
		AdFreePaging adFreePaging = adFreeService.getPaging(req);		
		//게시글 전체 조회
		List<Free> adfreeList = adFreeService.getList(adFreePaging);
		
		//조회결과 MODEL값 전달
		req.setAttribute("adfreeList", adfreeList);
	
		//페이징 정보 MODEL값 전달
		req.setAttribute("paging", adFreePaging);
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/admin/free/list.jsp").forward(req, resp);		
		
	}
}















