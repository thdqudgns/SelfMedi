package com.fulldoping.admin.free.dao.face;

import java.sql.Connection;
import java.util.List;

import com.fulldoping.admin.free.paging.AdFreePaging;
import com.fulldoping.free.dto.Free;
import com.fulldoping.free.dto.FreeFile;

public interface AdFreeDao {

	/**
	 * Free테이블 전체 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param paging - 페이징 정보 객체
	 * @return List<Free> - 테이블 전체 조회 결과 리스트
	 */
	public List<Free> selectAll(Connection conn, AdFreePaging adFreePaging);
	
	/**
	 * 총 게시글 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - Board테이블 전체 행 수 조회 결과
	 */
	public int selectCntAll(Connection conn);

	
	
	/**
	 * 조회된 게시글의 조회수 증가시키기
	 * 
	 * @param boardNo - 조회된 게시글 번호를 가진 객체
	 */
	public int updateHit(Connection conn, Free boardNo);

	/**
	 * id를 이용해 nick을 조회한다
	 * 
	 * @param viewFree - 조회할 id를 가진 객체
	 * @return String - 작성자 닉네임
	 */
	public String selectuserNickByUserId(Connection conn, Free viewFree);

	/**
	 * 첨부파일 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param viewFree - 첨부파일을 조회할 게시글번호 객체
	 * @return FreeFile - 조회된 첨부파일
	 */
	public FreeFile selectFile(Connection conn, Free viewFree);

	
	/**
	 * 특정 게시글 상세조회
	 * 
	 * @param conn - DB연결 객체
	 * @param boardNo - 조회할 boardno를 가진 객체
	 * @return Free - 조회된 결과 객체
	 */
	public Free selectFreeByBoardNo(Connection conn, Free boardNo);



	

}
