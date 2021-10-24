package com.fulldoping.member.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.member.dto.Member;
import com.fulldoping.member.dto.MemberFile;

public interface BusinessMemberService {

	/**
	 * 로그인 정보 추출하기
	 * 
	 * @param req - 요청 정보 객체
	 * @return - 사업자 로그인 정보
	 */
	public Member getLoginBusinessMember(HttpServletRequest req);
	
	/**
	 * 로그인 처리하기
	 * 
	 * @param member - 요청 정보 객체
	 * @return true - 인증됨, false - 인증되지 않음
	 */
	public boolean loginBusiness(Member member);
	
	/**
	 * 회원 (member) 정보 가져오기
	 * 
	 * @param member - 회원 아이디를 가진 객체
	 * @return Member - 조회된 회원 정보
	 */
	public Member infoBusiness(Member member);

	/**
	 * 회원가입 정보 추출하기
	 * 
	 * @param req - 요청정보 객체
	 * @return - 추출한 회원가입 정보
	 */
	public Member getJoinBusinessMember(HttpServletRequest req);

	/**
	 * 회원가입 처리하기
	 * 
	 * @param param - 회원가입 정보 객체
	 */
	public void joinBusiness(Member member);
	
	
	
	//----회원 파일 업로드 ---------------------------------------------
	
	/**
	 * 회원 파일 업로드 처리하기
	 * 
	 * @param req - HTTP요청 정보 객체
	 * @param resp - HTTP응답 정보 객체
	 */
	public void businessMemberFileUpload(HttpServletRequest req, HttpServletResponse resp);
	
	/**
	 * 회원 및 사업자 파일 정보 조회하기
	 * 
	 * @param req - 요청 정보 객체
	 * @return - 성공: 1, 실패: 0
	 */
	public int insertBusinessMember(Member member, MemberFile memberFile);
	
	/**
	 * 사업자 회원 파일 정보 추출하기
	 * 
	 * @param req - 요청 정보 객체

	 */
	public MemberFile businessFile(HttpServletRequest req);
	
	/**
	 * 파일 전체 목록을 조회하기
	 * 
	 * @return 조회된 전체 파일 목록
	 */
	public List<MemberFile> list();
	
}





