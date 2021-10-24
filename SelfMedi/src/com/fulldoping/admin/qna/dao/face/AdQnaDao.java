package com.fulldoping.admin.qna.dao.face;

import java.sql.Connection;
import java.util.List;

import com.fulldoping.QnA.dto.QnA;
import com.fulldoping.QnA.dto.QnAFile;
import com.fulldoping.admin.qna.paging.AdQnaPaging;

public interface AdQnaDao {

	/**
	 * Free테이블 전체 조회
	 * 페이징 처리 추가
	 * 
	 * @param conn - DB연결 객체
	 * @param paging - 페이징 정보 객체
	 * @return List<QnA> - Free테이블 전체 조회 결과 리스트
	 */
	public List<QnA> selectAll(Connection conn, AdQnaPaging adQnaPaging);

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
	public int updateHit(Connection conn, QnA boardNo);

	/**
	 * 특정 게시글 상세조회
	 * 
	 * @param conn - DB연결 객체
	 * @param boardNo - 조회할 boardno를 가진 객체
	 * @return  QnA - 조회된 결과 객체
	 */
	public QnA selectFreeByBoardNo(Connection conn, QnA boardNo);

	/**
	 * 첨부파일 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param viewFreeDeclare - 첨부파일을 조회할 게시글번호 객체
	 * @return  QnAFile - 조회된 첨부파일
	 */
	public QnAFile selectFile(Connection conn, QnA viewQna);

	/**
	 * 게시글 삭제
	 * 
	 * @param notice - 삭제할 게시글번호를 담은 객체
	 */
	public int commentsdelete(Connection conn, QnA qna);

	/**
	 * 게시글 삭제
	 * 
	 * @param notice - 삭제할 게시글번호를 담은 객체
	 */
	public int deletedeclare(Connection conn, QnA qna);

	/**
	 * 게시글 삭제
	 * 
	 * @param notice - 삭제할 게시글번호를 담은 객체
	 */
	public int deleteFile(Connection conn, QnA qna);

	/**
	 * 게시글 삭제
	 * 
	 * @param notice - 삭제할 게시글번호를 담은 객체
	 */
	public int delete(Connection conn, QnA qna);







	

}
