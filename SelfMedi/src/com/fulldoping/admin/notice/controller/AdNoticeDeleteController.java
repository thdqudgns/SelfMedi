package com.fulldoping.admin.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.admin.notice.service.face.AdNoticeService;
import com.fulldoping.admin.notice.service.impl.AdNoticeServiceImpl;
import com.fulldoping.notice.dto.Notice;

@WebServlet("/ad/notice/delete")
public class AdNoticeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private AdNoticeService adNoticeService = new AdNoticeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Notice notice = adNoticeService.getboardNo(req);
		
		adNoticeService.delete(notice);
		
		//목록으로 리다이렉트
		resp.sendRedirect("/ad/notice/list");	
	}
	
}
