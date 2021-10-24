package com.fulldoping.QnA.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.QnA.dto.QnA;
import com.fulldoping.QnA.dto.QnAComments;
import com.fulldoping.QnA.dto.QnAFile;
import com.fulldoping.QnA.service.face.QnAService;
import com.fulldoping.QnA.service.impl.QnAServiceImpl;

@WebServlet("/qna/view")
public class QnAViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//QnAService 객체 생성
    private QnAService qnaService = new QnAServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//전달파라미터 얻기 - boardno
		QnA boardno = qnaService.getBoardno(req);
		
		//상세보기 결과 조회
		QnA viewBoard = qnaService.getview(boardno);
		
		//조회결과 MODEL값 전달
		req.setAttribute("viewBoard", viewBoard);
		
		//닉네임 전달
		req.setAttribute("nick", qnaService.getNick(viewBoard));
		
		//첨부파일 정보 조회
		QnAFile boardFile = qnaService.getviewFile(viewBoard);
		
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("boardFile", boardFile);
		
		//댓글 전체 조회
		List<QnAComments> commentList = qnaService.getCommentList(boardno.getBoardNo());
				
		//조회결과 MODEL값 전달
		req.setAttribute("commentList", commentList);
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/qnaboard/view.jsp").forward(req, resp);		

	}

}
