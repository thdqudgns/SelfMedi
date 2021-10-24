package com.fulldoping.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.notice.dto.NoticeComments;
import com.fulldoping.notice.service.face.NoticeService;
import com.fulldoping.notice.service.impl.NoticeServiceImpl;

@WebServlet("/notice/comment/write")
public class NoticeCommentWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private NoticeService noticeService = new NoticeServiceImpl(); 
	
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("/notice/comment/write [POST]");
    	
		req.setCharacterEncoding("utf-8");
		System.out.print(req.getParameter("boardNo"));
		System.out.println(req.getParameter("content"));
		
		//작성댓글 삽입
		int commentno = noticeService.commentInsert(req);
    	
		//댓글 전체 조회
		List<NoticeComments> commentList = noticeService.getCommentList(Integer.parseInt(req.getParameter("boardNo")));
								
		//조회결과 MODEL값 전달
		req.setAttribute("commentList", commentList);
		
		System.out.println(commentList);
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/noticeboard/comment.jsp").forward(req, resp);
		
    }


}
