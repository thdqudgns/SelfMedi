package com.fulldoping.member.dao.face;

import java.sql.Connection;
import java.util.List;

import com.fulldoping.member.dto.Member;
import com.fulldoping.member.dto.MemberFile;

public interface BusinessMemberDao {

	/**
	 * userId 와 userPw가 일치하는 회원의 수를 조회
	 * 
	 * @param Member - 조회할 회원의 정보
	 * @return int - 1(존재하는 회원), 0(존재하지 않는 회원), -1(에러)
	 */
	public int selectCntBusinessMemberByUserIdUserPw(Connection conn, Member member);
	
	/**
	 * userNo을 이용해 회원정보 조회
	 * 
	 * @param member - 조회할 회원
	 * @return Member - 조회된 결과 객체
	 */
	public Member selectBusinessMemberByUserNo(Connection conn, Member member);
	
	/**
	 * 회원 번호 조회
	 * 
	 * 회원 테이블과 회원파일 테이블에 입력될 공통 userNo값을 시퀀스를 통해 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @return 회원 번호
	 */
	public int selectNextUserNo(Connection conn);

	/**
	 * 회원가입 정보 삽입하기
	 * 
	 * @param conn - DB연결 객체
	 * @param member - 회원가입 정보 객체
	 */
	public int insertBusiness(Connection conn, Member member);
	
	
	//---MemberFileUpload -------------------------------------------------

	/**
	 * 파일 정보 파라미터 삽입 및  파일 데이터 삽입
	 * 
	 * @param conn - DB연결 객체
	 * @param memberFile - 저장할 파일 정보 DTO 객체
	 * @return 삽입 수행 결과값 (1-정상 삽입, 0-실패)
	 */
	public int insertParamFile(Connection conn, MemberFile memberFile);

	/**
	 * 사용자파일 테이블 전체 조회
	 * 
	 * @param connection - DB연결 객체
	 * @return 테이블 전체 조회 결과 List
	 */
	public List<MemberFile> selectAll(Connection conn);

	

}
