package com.fulldoping.mypage.dao.face;

import java.sql.Connection;
import java.util.List;

import com.fulldoping.QnA.dto.QnA;
import com.fulldoping.free.dto.Free;
import com.fulldoping.member.dto.Member;
import com.fulldoping.notice.dto.Notice;
import com.fulldoping.selftest.dto.SelfTest;

public interface MypageDao {

	public Member getMemberInformation(Connection conn, int memberNo);

	public List<Notice> getNoticeInformation(Connection conn, int memberNo);

	public List<QnA> getQnAInformation(Connection conn, int memberNo);

	public List<Free> getFreeInformation(Connection conn, int memberNo);

	public List<SelfTest> getList(Connection connection, int userNo);

	public int delete(Connection conn, SelfTest selftestno);

	public int updateNickInformation(Connection conn, Member member);

	public int updatePwInformation(Connection conn, Member member);

	public int updateEmInformation(Connection conn, Member member);

	public int updatePhInformation(Connection conn, Member member);


	
}
