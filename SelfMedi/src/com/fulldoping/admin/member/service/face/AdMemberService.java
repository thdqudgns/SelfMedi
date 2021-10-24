package com.fulldoping.admin.member.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fulldoping.admin.member.paging.MemberPaging;
import com.fulldoping.member.dto.Member;

public interface AdMemberService {

	/**
	 * 회원 전체 조회
	 * 페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<Member> - 회원 전체 조회 결과 리스트
	 */
	public List<Member> getList(MemberPaging paging);
	
	/**
	 * 페이징 객체 생성
	 * 
	 * 요청파라미터 curPage를 구한다
	 * Member테이블과 curPage값을 이용하여 Paging객체를 구하여 반환한다
	 * 
	 * @param req - 요청정보 객체
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public MemberPaging getPaging(HttpServletRequest req);
	
	/**
	 * 요청파라미터 얻기
	 * 
	 * @param req - 요청정보객체
	 * @return Member - 전달파라미터 userNo를 포함한 객체
	 */
	public Member getUserNo(HttpServletRequest req);
	
	/**
	 * 주어진 userNo을 이용하여 게시글을 조회한다
	 * 
	 * @param userNo을 가지고 있는 객체
	 * @return Member - 전달파라미터 userNo을 포함한 객체
	 */
	public Member view(Member userNo);
	
	/**
	 * 회원 삭제
	 * 
	 * @param member - 삭제할 회원번호를 가진 객체
	 */
	public void delete(Member member);
	
//	/**
//	 * 중복체크
//	 * @param req - 입력값으로 전달받은 정보
//	 */
//	public void getOverlap(HttpServletRequest req);
	
}
