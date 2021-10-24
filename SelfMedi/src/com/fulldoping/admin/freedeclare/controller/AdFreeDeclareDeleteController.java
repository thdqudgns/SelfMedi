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


@WebServlet("/ad/freedeclare/delete")
public class AdFreeDeclareDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스 객체 생성
	private AdFreeDeclareService adFreeDeclareService = new AdFreeDeclareServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		FreeDeclare freeDeclare = adFreeDeclareService.getboardNo(req);
		
		adFreeDeclareService.delete(freeDeclare);
		
		//목록으로 리다이렉트
		resp.sendRedirect("/ad/freedeclare/list");	
		
	}
}
