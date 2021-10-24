package com.fulldoping.admin.notice.dao.face;

import java.sql.Connection;
import java.util.List;

import com.fulldoping.admin.notice.paging.AdNoticePaging;
import com.fulldoping.notice.dto.Notice;
import com.fulldoping.notice.dto.NoticeFile;

public interface AdNoticeDao {

	/**
	 * Free테이블 전체 조회
	 * 페이징 처리 추가
	 * 
	 * @param conn - DB연결 객체
	 * @param adNoticePaging - 페이징 정보 객체
	 * @return List<Notice> - Free테이블 전체 조회 결과 리스트
	 */
	public List<Notice> selectAll(Connection conn, AdNoticePaging adNoticePaging);

	/**
	 * 총 게시글 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - Free테이블 전체 행 수 조회 결과
	 */
	public int selectCntAll(Connection conn);

	/**
	 * 조회된 게시글의 조회수 증가시키기
	 * 
	 * @param conn - DB연결 객체
	 * @param boardNo - 조회된 게시글 번호를 가진 객체
	 * @return
	 */
	public int updateHit(Connection conn, Notice boardNo);

	/**
	 * 특정 게시글 상세조회
	 * 
	 * @param conn - DB연결 객체
	 * @param boardNo - 조회할 boardno를 가진 객체
	 * @return Notice - 조회된 결과 객체
	 */
	public Notice selectFreeByBoardNo(Connection conn, Notice boardNo);

	/**
	 * id를 이용해 nick을 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @param viewNotice - 조회할 id를 가진 객체
	 * @return String - 작성자 닉네임
	 */
	public String selectuserNickByUserId(Connection conn, Notice viewNotice);

	/**
	 * 첨부파일 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param viewNotice - 첨부파일을 조회할 게시글번호 객체
	 * @return NoticeFile - 조회된 첨부파일
	 */
	public NoticeFile selectFile(Connection conn, Notice viewNotice);


	/**
	 * 게시글 입력
	 * 
	 * @param notice - 삽입될 게시글 내용
	 */
	public int insert(Connection conn, Notice notice);
	
	/**
	 * 다음 게시글 번호 조회
	 * 
	 * 	게시글 테이블과 첨부파일 테이블에 입력될 공통 boardNo값을 시퀀스를 통해 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @return 다음 게시글 번호
	 */
	public int selectNextBoardNo(Connection conn);

	/**
	 * userNo로 userId조회
	 * 
	 * @param conn
	 * @param userNo
	 * @return
	 */
	public String getUserId(Connection conn, int userNo);

	/**
	 * 첨부파일 입력
	 * 
	 * @param conn - DB연결 객체
	 * @param noticeFile - 첨부파일 정보
	 * @return 삽입 결과
	 */
	public int insertFile(Connection conn, NoticeFile noticeFile);

	/**
	 * 게시글 수정 
	 * 
	 * @param notice - 수정할 내용을 담은 객체
	 */
	public int update(Connection conn, Notice notice);

	
	/**
	 * 게시글에 첨부된 파일 기록 삭제
	 * 
	 * @param notice - 삭제할 게시글번호를 담은 객체
	 */
	public int deleteFile(Connection conn, Notice notice);

	/**
	 * 게시글 삭제
	 * 
	 * @param notice - 삭제할 게시글번호를 담은 객체
	 */
	public int delete(Connection conn, Notice notice);

	//댓글 삭제
	public int commentsdelete(Connection conn, Notice notice);

}










