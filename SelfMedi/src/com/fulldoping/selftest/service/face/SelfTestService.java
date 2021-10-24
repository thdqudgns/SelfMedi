package com.fulldoping.selftest.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fulldoping.selftest.dto.SelfTest;

public interface SelfTestService {

	/**
	 * req영역 안의 input된 정보들을 DB에 집어넣는 메소드
	 * 
	 * @param req 요청파라미터
	 */
	public SelfTest insert(HttpServletRequest req);

	/**
	 * 자가진단 전체 조회
	 * 
	 * @return 조회결과 리스트
	 */
	public List<SelfTest> getList(int userno);

	/**
	 * 전달 파라미터를 DTO로 저장하여 반환 - 진단서 번호만 저장
	 * 
	 * @param req - 요청 파라미터
	 * @return - 반환 DTO 객체
	 */
	public SelfTest getTestno(HttpServletRequest req);

	/**
	 * 진단서 번호로 상세 결과 조회
	 * 
	 * @param selftestno - 진단서 번호
	 * @return DTO객체에 data들을 담아서 반환
	 */
	public SelfTest view(SelfTest selftestno);

	/**
	 * 자가진단서 삭제
	 * 
	 * @param selftestno 자가진단서 번호
	 */
	public void delete(SelfTest selftestno);


}
