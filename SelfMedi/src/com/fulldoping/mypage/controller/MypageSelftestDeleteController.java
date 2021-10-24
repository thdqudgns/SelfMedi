package com.fulldoping.mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.mypage.service.face.MypageService;
import com.fulldoping.mypage.service.impl.MypageServiceImpl;
import com.fulldoping.selftest.dto.SelfTest;

@WebServlet("/mypage/selftest/delete")
public class MypageSelftestDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MypageService mypageService = new MypageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//클라이언트의 동작으로 부터 진단서 번호 전달파라미터 얻기 -> DTO타입으로 저장
		SelfTest selftestno = mypageService.getTestno(req);
		
		//DB의 데이터를 삭제하는 코드
		mypageService.delete(selftestno);
		
		//result 페이지에서 다시 list 페이지로 새로고침 한다.
		resp.sendRedirect("/mypage");
	}
}
