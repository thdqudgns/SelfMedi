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
import com.fulldoping.notice.service.face.NoticeService;
import com.fulldoping.notice.service.impl.NoticeServiceImpl;


@WebServlet("/notice/comment")
public class NoticeCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private NoticeService noticeService = new NoticeServiceImpl(); 
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    	//전달파라미터 얻기 - boardno
    	Notice boardno = noticeService.getboardNo(req);
    	System.out.println(boardno.getBoardNo());
    	
    	//댓글 전체 조회
    	List<NoticeComments> commentList = noticeService.getCommentList(boardno.getBoardNo());
		
		//조회결과 MODEL값 전달
		req.setAttribute("commentList", commentList);
		
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/noticeboard/comment.jsp").forward(req, resp);
    	
    }
}
