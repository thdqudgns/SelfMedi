package com.fulldoping.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.notice.dto.Notice;
import com.fulldoping.notice.dto.NoticeComments;
import com.fulldoping.notice.dto.NoticeFile;
import com.fulldoping.notice.service.face.NoticeService;
import com.fulldoping.notice.service.impl.NoticeServiceImpl;


@WebServlet("/notice/view")
public class NoticeViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스 객체 생성
	private NoticeService noticeService = new NoticeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//전달파라미터 얻기 - boardNo
		Notice boardNo = noticeService.getboardNo(req);
		
		//상세보기 결과 조회
		Notice viewNotice = noticeService.view(boardNo);
		
		//조회결과 MODEL값 전달
		req.setAttribute("viewNotice", viewNotice);
		
		//닉네임 전달
		req.setAttribute("userNick", noticeService.getuserNick(viewNotice));
		
		//첨부파일 정보 조회
		NoticeFile noticeFile = noticeService.viewFile(viewNotice);
		
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("noticeFile", noticeFile);
		
		//댓글 전체 조회
    	List<NoticeComments> commentList = noticeService.getCommentList(boardNo.getBoardNo());
		
		//조회결과 MODEL값 전달
		req.setAttribute("commentList", commentList);

		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/noticeboard/view.jsp").forward(req, resp);		
		
		
	}
}
