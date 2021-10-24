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

/**
 * Servlet implementation class AdNoticeUpdateController
 */
@WebServlet("/ad/notice/update")
public class AdNoticeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdNoticeService adNoticeService = new AdNoticeServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		//전달파라미터 얻기 - boardNo
		Notice boardNo = adNoticeService.getboardNo(req);
			System.out.println(boardNo);
		//상세보기 결과 조회
			Notice updateNotice = adNoticeService.view(boardNo); // 상세보기 결과 조회 데이터 넘어오는거 확인
			
		//닉네임 전달
		req.setAttribute("userNick", adNoticeService.getuserNick(updateNotice)); // 닉네임 넘어오는거 확인
			
		//조회결과 MODEL값 전달
		req.setAttribute("updateNotice", updateNotice); // 데이터 넘어 오는거 확인

		//첨부파일 정보 VIEW에 전달
		NoticeFile noticeFile = adNoticeService.viewFile(updateNotice);  // 넘어 오는거 확인 환료
		
		req.setAttribute("noticeFile", noticeFile); // 넘어 오는거 확인

		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/admin/notice/update.jsp").forward(req, resp);		
		
		
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		adNoticeService.update(req);
		
		resp.sendRedirect("/ad/notice/list");
	}
}
