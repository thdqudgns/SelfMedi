package com.fulldoping.admin.qnadeclare.dao.face;

import java.sql.Connection;
import java.util.List;

import com.fulldoping.QnA.dto.QnADeclare;
import com.fulldoping.QnA.dto.QnAFile;
import com.fulldoping.admin.qnadeclare.paging.QnADeclarePaging;

public interface AdQnADeclareDao {

	/**
	 * QnADeclare테이블 전체 조회
	 * 페이징 처리 추가
	 * 
	 * @param conn - DB연결 객체
	 * @param paging - 페이징 정보 객체
	 * @return List<QnADeclare> - QnADeclare테이블 전체 조회 결과 리스트
	 */
	public List<QnADeclare> selectAll(Connection conn, QnADeclarePaging qnaDeclarePaging);

	/**
	 * 총 게시글 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - QnA테이블 전체 행 수 조회 결과
	 */	
	public int selectCntAll(Connection conn);

	/**
	 * 조회된 게시글의 조회수 증가시키기
	 * 
	 * @param conn - DB연결 객체
	 * @param boardNo - 조회된 게시글 번호를 가진 객체
	 * @return
	 */
	public int updateHit(Connection conn, QnADeclare boardNo);

	/**
	 * 특정 게시글 상세조회
	 * 
	 * @param conn - DB연결 객체
	 * @param boardNo - 조회할 boardno를 가진 객체
	 * @return QnADeclare - 조회된 결과 객체
	 */
	public QnADeclare selectQnAByBoardNo(Connection conn, QnADeclare boardNo);

	/**
	 * id를 이용해 nick을 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @param viewQnADeclare - 조회할 id를 가진 객체
	 * @return String - 작성자 닉네임
	 */
	public String selectuserNickByUserId(Connection conn, QnADeclare viewQnaDeclare);

	/**
	 * 첨부파일 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param viewQnADeclare - 첨부파일을 조회할 게시글번호 객체
	 * @return QnAFile - 조회된 첨부파일
	 */
	public QnAFile selectFile(Connection conn, QnADeclare viewQnaDeclare);

	/**
	 * 게시글에 첨부된 파일 기록 삭제
	 * 
	 * @param qnaDeclare - 삭제할 게시글번호를 담은 객체
	 */
	public int deleteFile(Connection conn, QnADeclare qnaDeclare);

	/**
	 * 게시글 삭제
	 * 
	 * @param qnaDeclare - 삭제할 게시글번호를 담은 객체
	 */
	public int deletedeclare(Connection conn, QnADeclare qnaDeclare);

	/**
	 * Free 게시글 삭제
	 * 
	 * @param qnaDeclare - 삭제할 게시글번호를 담은 객체
	 */
	public int deletecomment(Connection conn, QnADeclare qnaDeclare);

	/**
	 * Free 게시글 삭제
	 * 
	 * @param qnaDeclare - 삭제할 게시글번호를 담은 객체
	 */
	public int deleteqna(Connection conn, QnADeclare qnaDeclare);



}
