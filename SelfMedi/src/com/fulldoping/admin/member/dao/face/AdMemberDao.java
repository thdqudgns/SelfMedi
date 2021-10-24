package com.fulldoping.admin.member.dao.face;

import java.sql.Connection;
import java.util.List;

import com.fulldoping.admin.member.paging.MemberPaging;
import com.fulldoping.member.dto.Member;

public interface AdMemberDao {
	
	/**
	 * Member테이블 전체 조회
	 * 페이징 처리 추가
	 * 
	 * @param conn - DB연결 객체
	 * @param paging - 페이징 정보 객체
	 * @return List<Member> - Member테이블 전체 조회 결과 리스트
	 */
	public List<Member> selectAll(Connection conn, MemberPaging paging);
	
	/**
	 * 총 게시글 수 조회
	 * 
	 * @param connection - DB연결 객체
	 * @return int - Member테이블 전체 행 수 조회 결과
	 */
	public int selectCntAll(Connection conn);
	
	/**
	 * 특정 회원 조회
	 * @param conn
	 * @param userNo
	 * @return
	 */
	public Member selectMemberUserNo(Connection conn, Member userNo);
	
	/**
	 * 회원 삭제
	 * 
	 * @param conn - DB연결 객체
	 * @param member - 삭제할 회원번호를 담은 객체
	 * @return int - Member테이블 삭제
	 */
	public int delete(Connection conn, Member member);
	
	/**
	 * 사업자회원정보에 첨부된 파일 기록 삭제
	 * @param conn - DB연결 객체
	 * @param member - 삭제할 회원번호를 담은 객체
	 * @return int - 첨부파일 삭제
	 */
	public int deleteFile(Connection conn, Member member);
}
	

