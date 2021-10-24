package com.fulldoping.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.notice.dto.Notice;
import com.fulldoping.notice.paging.NoticePaging;
import com.fulldoping.notice.service.face.NoticeService;
import com.fulldoping.notice.service.impl.NoticeServiceImpl;


@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스 객체 생성
	private NoticeService noticeService = new NoticeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청 파라미터를 전달하여 Paging객체 생성하기
		NoticePaging paging = noticeService.getPaging(req);
		
		//게시글 전체 조회
		List<Notice> noticeList = noticeService.getList(paging);
		System.out.println("NOTICELIST" + noticeList);
		//조회 결과 MODEL값 전달
		req.setAttribute("noticeList", noticeList);
		
		//페이징 정보 MODEL값 전달
		req.setAttribute("paging", paging);
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/noticeboard/list.jsp").forward(req, resp);
		
	}
}














