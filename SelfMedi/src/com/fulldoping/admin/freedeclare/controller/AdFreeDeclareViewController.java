package com.fulldoping.admin.freedeclare.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.admin.freedeclare.service.face.AdFreeDeclareService;
import com.fulldoping.admin.freedeclare.service.impl.AdFreeDeclareServiceImpl;
import com.fulldoping.free.dto.FreeDeclare;
import com.fulldoping.free.dto.FreeFile;



@WebServlet("/ad/freedeclare/view")
public class AdFreeDeclareViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스 객체 생성
	private AdFreeDeclareService adFreeDeclareService = new AdFreeDeclareServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//전달파라미터 얻기 - boardNo
		FreeDeclare boardNo = adFreeDeclareService.getboardNo(req);
		
		//상세보기 결과 조회
		FreeDeclare viewFreeDeclare = adFreeDeclareService.view(boardNo);
		
		//조회결과 MODEL값 전달
		req.setAttribute("viewFreeDeclare", viewFreeDeclare);
		
		//닉네임 전달
//		req.setAttribute("userNick", adFreeDeclareService.getuserNick(viewFreeDeclare));
		
		//첨부파일 정보 조회
		FreeFile freeFile = adFreeDeclareService.viewFile(viewFreeDeclare);
		
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("FreeFile", freeFile);

		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/admin/declare/view.jsp").forward(req, resp);		
		
		
	}
		
}
	

