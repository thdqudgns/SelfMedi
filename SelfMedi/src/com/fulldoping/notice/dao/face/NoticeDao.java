package com.fulldoping.notice.dao.face;

import java.sql.Connection;
import java.util.List;

import com.fulldoping.notice.dto.Notice;
import com.fulldoping.notice.dto.NoticeComments;
import com.fulldoping.notice.dto.NoticeFile;
import com.fulldoping.notice.paging.NoticePaging;

public interface NoticeDao {

	/**
	 * Notice 테이블 전체 조회
	 * 페이징 처리 추가
	 * 
	 * @param connection
	 * @param paging
	 * @return
	 */
	public List<Notice> selectAll(Connection conn, NoticePaging paging);

	/**
	 * 총 게시글 조회
	 * 
	 * @param conn -  DB연결 객체
	 * @return int - Notice테이블 전체 행 수 조회 결과
	 */
	public int selectCntAll(Connection conn);

	/**
	 * 특정 게시글 상세조회
	 * 
	 * @param conn - DB연결 객체
	 * @param boardNo - 조회할 boardno를 가진 객체
	 * @return Notice - 조회된 결과 객체
	 */
	public Notice selectFreeByBoardNo(Connection conn, Notice boardNo);
	
	/**
	 * 조회된 게시글의 조회수 증가시키기
	 * 
	 * @param conn - DB연결 객체
	 * @param boardNo - 조회된 게시글 번호를 가진 객체
	 * @return
	 */
	public int updateHit(Connection conn, Notice boardNo);


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
	
	//댓글
	public NoticeFile selectFile(Connection conn, Notice viewNotice);

	public List<NoticeComments> selectAllComments(Connection connection, int boardnum);

	public int selectNextCommentno(Connection conn);

	public String getUserNick(Connection conn, Integer attribute);

	public int commentsinsert(Connection conn, NoticeComments comment);

	public NoticeComments selectcommentBycommentno(Connection conn, NoticeComments comment);

	public int commentsupdate(Connection conn, NoticeComments comment);

	public int commentsdelete(Connection conn, NoticeComments comment);
}
