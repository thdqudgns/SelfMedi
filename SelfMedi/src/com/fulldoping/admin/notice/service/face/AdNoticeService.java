package com.fulldoping.admin.notice.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fulldoping.admin.notice.paging.AdNoticePaging;
import com.fulldoping.notice.dto.Notice;
import com.fulldoping.notice.dto.NoticeFile;

public interface AdNoticeService {

	
	/**
	 * 페이징 처리 추가 된 게시글 전체 조회
	 * 
	 * @param adNoticePaging - 페이징 정보 객체
	 * @return List<Notice> - 게시글 전체 조회 결과 리스트
	 */
	public List<Notice> getList(AdNoticePaging adNoticePaging);
	
	/**
	 * 페이징 객체 생성
	 * 
	 * 요청파라미터 curPage를 구한다
	 * Notice테이블과 curPage값을 이용하여 Paging객체를 구하여 반환한다
	 * 
	 * @param req - 요청정보 객체
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public AdNoticePaging getPaging(HttpServletRequest req);

	/**
	 * 요청 파라미터 얻기
	 * 
	 * @param req - 요청정보 객체
	 * @return Notice - 전달파라미터 boardNo를 포함한 객체
	 */
	public Notice getboardNo(HttpServletRequest req);
	
	/**
	 * 주어진 boardNo을 이용하여 게시글을 조회한다
	 * 
	 * @param boardNo - boardNo를 가지고 있는 객체
	 * @return Notice - 조회된 게시글
	 */
	public Notice view(Notice boardNo);

	/**
	 * Notice 객체의 id를 이용한 닉네임 조회
	 * 
	 * @param viewNotice - 조회할 게시글 정보
	 * @return String - 게시글 작성자의 닉네임
	 */
	public String getuserNick(Notice viewNotice);

	/**
	 * 첨부파일 정보 조회
	 * 
	 * @param viewNotice - 첨부파일과 연결된 게시글번호를 포함한 DTO객체
	 * @return NoticeFile - 첨부파일 정보 DTO객체
	 */
	public NoticeFile viewFile(Notice viewNotice);

	/**
	 * 게시글 작성
	 * 입력한 게시글 내용을 DB에 저장
	 * 
	 * 첨부파일을 함께 업로드 할 수 있도록 처리
	 * 
	 * @param req - 요청정보 객체(게시글 내용 + 첨부파일)
	 */
	public void write(HttpServletRequest req);

	/**
	 * 게시글 수정
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void update(HttpServletRequest req);
	
	/**
	 * 게시글 삭제
	 * 
	 * @param notice - 삭제할 게시글 번호를 가진 객체
	 */
	public void delete(Notice notice);


}
