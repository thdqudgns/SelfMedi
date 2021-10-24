package com.fulldoping.admin.free.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.admin.free.service.face.AdFreeService;
import com.fulldoping.admin.free.service.impl.AdFreeServiceImpl;
import com.fulldoping.free.dto.Free;
import com.fulldoping.free.dto.FreeFile;

@WebServlet("/ad/free/view")
public class AdFreeViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//FreeService 객체생성
	private AdFreeService adFreeService = new AdFreeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		System.out.println("view [GET]");
		
		//전달파라미터 얻기 - boardNo
		Free boardNo = adFreeService.getboardNo(req);
		
		//상세보기 결과 조회
		Free viewFree = adFreeService.view(boardNo);
		
		//조회결과 MODEL값 전달
		req.setAttribute("viewFree", viewFree);
		
		//닉네임 전달
		req.setAttribute("userNick", adFreeService.getuserNick(viewFree));
		
		//첨부파일 정보 조회
		FreeFile freeFile = adFreeService.viewFile(viewFree);
		
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("freeFile", freeFile);
			
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/admin/free/view.jsp").forward(req, resp);	
		
	}
}
