package com.fulldoping.mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fulldoping.QnA.dto.QnA;
import com.fulldoping.free.dto.Free;
import com.fulldoping.member.dto.Member;
import com.fulldoping.mypage.service.face.MypageService;
import com.fulldoping.mypage.service.impl.MypageServiceImpl;
import com.fulldoping.notice.dto.Notice;
import com.fulldoping.selftest.dto.SelfTest;


@WebServlet("/mypage")
public class MypageViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private MypageService mypageService = new MypageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session.getAttribute("login") != null) {
			
			//맴버 정보
			Member member = mypageService.getLoginMemberInformation(session);
			System.out.println(member.toString());

			//조회결과 MODEL값 전달
			req.setAttribute("member", member);
			
			//자가진단데이터 전체 조회
			List<SelfTest> selfTestlist = mypageService.getList((int)session.getAttribute("userNo"));
			
			//조회결과 값 view에 전달
			req.setAttribute("selfTestList", selfTestlist);
			 
			//공지사항게시판 게시글 전체 조회
			 List<Notice> NoticeList = mypageService.getNoticeList(session);
				
			//조회결과 MODEL값 전달
			req.setAttribute("NoticeList", NoticeList);
			 
			//자유게시판 게시글 전체 조회
			 List<Free> FreeList = mypageService.getFreeList(session);
				
			//조회결과 MODEL값 전달
			req.setAttribute("FreeList", FreeList);
			
			//QnA게시글 전체 조회
			 List<QnA> QnAList = mypageService.getQnAList(session);
				
			//조회결과 MODEL값 전달
			req.setAttribute("QnAList", QnAList);
			
			//VIEW 지정 - forward
			req.getRequestDispatcher("/WEB-INF/views/mypage/mypage.jsp").forward(req, resp);
			
		}else {
			 //로그인 페이지로 리다이렉트
		      resp.sendRedirect("/member/login");
		}
	}

}
