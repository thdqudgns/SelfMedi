package com.fulldoping.mypage.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fulldoping.QnA.dto.QnA;
import com.fulldoping.free.dto.Free;
import com.fulldoping.member.dto.Member;
import com.fulldoping.notice.dto.Notice;
import com.fulldoping.selftest.dto.SelfTest;

public interface MypageService {

	public Member getLoginMemberInformation(HttpSession session);

	public List<QnA> getQnAList(HttpSession session);

	public List<Free> getFreeList(HttpSession session);

	public List<Notice> getNoticeList(HttpSession session);

	public List<SelfTest> getList(int userNo);

	public SelfTest getTestno(HttpServletRequest req);

	public void delete(SelfTest selftestno);

	public Member updateNick(HttpServletRequest req);

	public Member updatePw(HttpServletRequest req);

	public Member updateEm(HttpServletRequest req);

	public Member updatePh(HttpServletRequest req);


}
