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
import com.fulldoping.notice.dto.NoticeFile;

@WebServlet("/ad/notice/view")
public class AdNoticeViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdNoticeService adNoticeService = new AdNoticeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//전달파라미터 얻기 - boardNo
		Notice boardNo = adNoticeService.getboardNo(req);
		
		//상세보기 결과 조회
		Notice viewNotice = adNoticeService.view(boardNo);
		
		//조회결과 MODEL값 전달
		req.setAttribute("viewNotice", viewNotice);
		
		//닉네임 전달
		req.setAttribute("userNick", adNoticeService.getuserNick(viewNotice));
		
		//첨부파일 정보 조회
		NoticeFile noticeFile = adNoticeService.viewFile(viewNotice);
		
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("noticeFile", noticeFile);

		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/admin/notice/view.jsp").forward(req, resp);		
		
		
	}
}
