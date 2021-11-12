package com.fulldoping.admin.free.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.free.dto.Free;
import com.fulldoping.free.service.face.FreeService;
import com.fulldoping.free.service.impl.FreeServiceImpl;

@WebServlet("/ad/free/delete")
public class AdFreeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//AdFreeService 객체생성
	private FreeService freeService = new FreeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Free free = freeService.getboardNo(req);
				
		freeService.delete(free);
		
		//목록으로 리다이렉트
		resp.sendRedirect("/ad/free/list");	
		
		//수정
		//수정22
		//수정33
	}

}
