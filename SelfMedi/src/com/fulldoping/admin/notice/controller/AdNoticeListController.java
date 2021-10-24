package com.fulldoping.admin.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.admin.notice.paging.AdNoticePaging;
import com.fulldoping.admin.notice.service.face.AdNoticeService;
import com.fulldoping.admin.notice.service.impl.AdNoticeServiceImpl;
import com.fulldoping.notice.dto.Notice;


@WebServlet("/ad/notice/list")
public class AdNoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdNoticeService adNoticeService = new AdNoticeServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		//요청 파라미터를 전달하여 Paging객체 생성하기
		AdNoticePaging adNoticePaging = adNoticeService.getPaging(req);
		
		//게시글 전체 조회
		List<Notice> adNoticeList = adNoticeService.getList(adNoticePaging);
		
		//조회 결과 MODEL값 전달
		req.setAttribute("adNoticeList", adNoticeList);
		
		//페이징 정보 MODEL값 전달
		req.setAttribute("paging", adNoticePaging);
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/admin/notice/list.jsp").forward(req, resp);
		
		}
}
