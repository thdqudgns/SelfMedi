package com.fulldoping.free.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.free.dto.Free;
import com.fulldoping.free.dto.FreeFile;
import com.fulldoping.free.service.face.FreeService;
import com.fulldoping.free.service.impl.FreeServiceImpl;


@WebServlet("/free/update")
public class FreeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스 객체 생성
	private FreeService freeService = new FreeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//전달파라미터 얻기 - boardNo
		Free boardNo = freeService.getboardNo(req);
			System.out.println(boardNo);
		//상세보기 결과 조회
		Free updateFree = freeService.view(boardNo); // 상세보기 결과 조회 데이터 넘어오는거 확인
			
		//닉네임 전달
		req.setAttribute("userNick", freeService.getuserNick(updateFree)); // 닉네임 넘어오는거 확인
			
		//조회결과 MODEL값 전달
		req.setAttribute("updateFree", updateFree); // 데이터 넘어 오는거 확인

		//첨부파일 정보 VIEW에 전달
		FreeFile freeFile = freeService.viewFile(updateFree);  // 넘어 오는거 확인 환료
		
		req.setAttribute("freeFile", freeFile); // 넘어 오는거 확인

		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/freeboard/update.jsp").forward(req, resp);		
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		freeService.update(req);
		
		resp.sendRedirect("/free/list");
	}
}
