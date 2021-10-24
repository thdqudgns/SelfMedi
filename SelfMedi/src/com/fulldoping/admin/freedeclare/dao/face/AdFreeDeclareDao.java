package com.fulldoping.admin.freedeclare.dao.face;

import java.sql.Connection;
import java.util.List;

import com.fulldoping.admin.freedeclare.paging.FreeDeclarePaging;
import com.fulldoping.free.dto.FreeDeclare;
import com.fulldoping.free.dto.FreeFile;

public interface AdFreeDeclareDao {

	/**
	 * Free테이블 전체 조회
	 * 페이징 처리 추가
	 * 
	 * @param conn - DB연결 객체
	 * @param paging - 페이징 정보 객체
	 * @return List<FreeDeclare> - Free테이블 전체 조회 결과 리스트
	 */
	public List<FreeDeclare> selectAll(Connection conn, FreeDeclarePaging freeDeclarePaging);

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
	public int updateHit(Connection conn, FreeDeclare boardNo);

	/**
	 * 특정 게시글 상세조회
	 * 
	 * @param conn - DB연결 객체
	 * @param boardNo - 조회할 boardno를 가진 객체
	 * @return FreeDeclare - 조회된 결과 객체
	 */
	public FreeDeclare selectFreeByBoardNo(Connection conn, FreeDeclare boardNo);

	/**
	 * id를 이용해 nick을 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @param viewFreeDeclare - 조회할 id를 가진 객체
	 * @return String - 작성자 닉네임
	 */
//	public String selectuserNickByUserId(Connection conn, FreeDeclare viewFreeDeclare);

	/**
	 * 첨부파일 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param viewFreeDeclare - 첨부파일을 조회할 게시글번호 객체
	 * @return FreeFile - 조회된 첨부파일
	 */
	public FreeFile selectFile(Connection conn, FreeDeclare viewFreeDeclare);

	/**
	 * 게시글에 첨부된 파일 기록 삭제
	 * 
	 * @param freeDeclare - 삭제할 게시글번호를 담은 객체
	 */
	public int deleteFile(Connection conn, FreeDeclare freeDeclare);

	/**
	 * 게시글 삭제
	 * 
	 * @param freeDeclare - 삭제할 게시글번호를 담은 객체
	 */
	public int delete(Connection conn, FreeDeclare freeDeclare);

	/**
	 * Free 게시글 삭제
	 * 
	 * @param freeDeclare - 삭제할 게시글번호를 담은 객체
	 */
	public int deletefree(Connection conn, FreeDeclare freeDeclare);
	/**
	 * Free 게시글 삭제
	 * 
	 * @param freeDeclare - 삭제할 게시글번호를 담은 객체
	 */
	public int deletecomments(Connection conn, FreeDeclare freeDeclare);

}
