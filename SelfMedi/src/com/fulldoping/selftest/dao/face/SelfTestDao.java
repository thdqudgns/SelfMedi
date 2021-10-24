package com.fulldoping.selftest.dao.face;

import java.sql.Connection;
import java.util.List;

import com.fulldoping.selftest.dto.SelfTest;

public interface SelfTestDao {

	/**
	 * 자가진단서에 입력한 데이터를 DB에 저장하는 메소드
	 * 
	 * @param conn - DB연결객체
	 * @param selftest - 데이터들을 저장하고 있는 DTO객체
	 * @return - insert 성공시 1 반환하여 ServiceImpl의 if문이 수행되게 한다. 
	 */
	public int insert(Connection conn, SelfTest selftest);

	/**
	 * 유저 번호 반환 메소드
	 * 
	 * @param connection - DB연결객체
	 * @return 유저 번호 반환
	 */
//	public int getUserno(Connection connection);

	/**
	 * 게시글 전체 조회
	 * 
	 * @param conn - DB 연결 객체
	 * @return - 전체목록 반환
	 */
	public List<SelfTest> getList(Connection conn, int userno);

	/**
	 * 특정 진단서 내역 조회
	 * 
	 * @param conn - DB연결
	 * @param selftestno - 조회할 넘버를 가진 객체
	 * @return - 조회된 결과를 저장해 반환할 객체
	 */
	public SelfTest viewByTestno(Connection conn, SelfTest selftestno);

	/**
	 * 자가진단서 삭제
	 * 
	 * @param conn - DB연결
	 * @param selftestno - 진단서 번호
	 * @return 삭제 성공시 1 반환
	 */
	public int delete(Connection conn, SelfTest selftestno);



}
