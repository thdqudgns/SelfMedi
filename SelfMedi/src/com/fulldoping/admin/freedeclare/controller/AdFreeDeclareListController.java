package com.fulldoping.admin.freedeclare.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.admin.freedeclare.paging.FreeDeclarePaging;
import com.fulldoping.admin.freedeclare.service.face.AdFreeDeclareService;
import com.fulldoping.admin.freedeclare.service.impl.AdFreeDeclareServiceImpl;
import com.fulldoping.free.dto.FreeDeclare;


@WebServlet("/ad/freedeclare/list")
public class AdFreeDeclareListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	//서비스 객체 생성
	private AdFreeDeclareService adFreeDeclareService = new AdFreeDeclareServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		//요청 파라미터를 전달하여 Paging객체 생성하기
		FreeDeclarePaging freeDeclarePaging = adFreeDeclareService.getPaging(req);
		
		//게시글 전체 조회
		List<FreeDeclare> freeDeclareList = adFreeDeclareService.getList(freeDeclarePaging);
		
		//조회 결과 MODEL값 전달
		req.setAttribute("freeDeclareList", freeDeclareList);
		
		//페이징 정보 MODEL값 전달
		req.setAttribute("paging", freeDeclarePaging);
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/admin/declare/list.jsp").forward(req, resp);
	}
		
}


