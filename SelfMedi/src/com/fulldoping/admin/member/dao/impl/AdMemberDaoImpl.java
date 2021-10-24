package com.fulldoping.admin.member.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fulldoping.admin.member.dao.face.AdMemberDao;
import com.fulldoping.admin.member.paging.MemberPaging;
import com.fulldoping.common.JDBCTemplate;
import com.fulldoping.member.dto.Member;

public class AdMemberDaoImpl implements AdMemberDao {
	
	private PreparedStatement ps = null; // SQL 수행 객체;
	private ResultSet rs = null; // SQL 조회 결과 객체
	
	@Override
	public List<Member> selectAll(Connection conn, MemberPaging paging) {
		//SQL작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, member.* FROM (";
		sql += "		SELECT";
		sql += "			userNo, userName, userNick";
		sql += "			, joinDate";
		sql += "		FROM member";
		sql += "		ORDER BY userNo ASC";
		sql += "	) member";
		sql += " ) MEMBER";
		sql += " WHERE rnum BETWEEN ? AND ?";

		//결과 저장할 List
		List<Member> memberList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장

			//조회 결과 처리
			while(rs.next()) {
				Member member = new Member(); //결과값 저장 객체

				//결과값 한 행 처리
				member.setUserNo( rs.getInt("userNo") );
//				member.setUserId( rs.getString("userId") );
//				member.setUserPw( rs.getString("userPw") );
				member.setUserName( rs.getString("userName") );
				member.setUserNick( rs.getString("userNick") );
//				member.setUserPh( rs.getString("userPh") );
//				member.setUserEm( rs.getString("userEm") );
//				member.setUserGen( rs.getString("userGen") );
//				member.setUserBirth( rs.getString("userBirth") );
				member.setJoinDate( rs.getDate("joinDate") );
//				member.setBusinessNo( rs.getInt("businessNo") );

				//리스트에 결과값 저장
				memberList.add(member);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		//최종 결과 반환
		return memberList;
	}

	@Override
	public int selectCntAll(Connection conn) {
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM member";

		//총 게시글 수
		int count = 0;

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return count;
	}
	
	@Override
	public Member selectMemberUserNo(Connection conn, Member userNo) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM member";
		sql += " WHERE userNo = ?";
		
		//결과 저장할 Board객체
		Member viewMember = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, userNo.getUserNo()); //조회할 회원 번호 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				viewMember = new Member(); //결과값 저장 객체
				
				//결과값 한 행 처리
				viewMember.setUserNo( rs.getInt("userNo") );
				viewMember.setUserName( rs.getString("userName") );
				viewMember.setUserNick( rs.getString("userNick") );
				viewMember.setJoinDate( rs.getDate("joinDate") );
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return viewMember;
	}

	@Override
	public int delete(Connection conn, Member member) {
		
		System.out.println("member" + member);
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE member";
		sql += " WHERE userNo = ?";

		//DB 객체
		PreparedStatement ps = null; 

		int res = -1;

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, member.getUserNo());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
		
	}
	@Override
	public int deleteFile(Connection conn, Member member) {
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE memberfile";
		sql += " WHERE userNo = ?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, member.getUserNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
}
