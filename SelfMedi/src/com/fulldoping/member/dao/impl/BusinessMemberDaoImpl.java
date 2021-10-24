package com.fulldoping.member.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fulldoping.common.JDBCTemplate;
import com.fulldoping.member.dao.face.BusinessMemberDao;
import com.fulldoping.member.dto.Member;
import com.fulldoping.member.dto.MemberFile;

public class BusinessMemberDaoImpl implements BusinessMemberDao {

	private PreparedStatement ps = null; // SQL 수행 객체;
	private ResultSet rs = null; // SQL 조회 결과 객체
	
	@Override
	public int selectCntBusinessMemberByUserIdUserPw(Connection conn, Member member) {
		
		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM member";
		sql += " WHERE 1=1";
		//sql += "	AND userNo = ? ";
		sql += "	AND userId = ? ";
		sql += " 	AND userPw = ? ";

		// 결과 저장할 변수
		int cnt = -1;

		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체

			//ps.setInt(1, member.getUserNo());
			ps.setString(1, member.getUserId());
			ps.setString(2, member.getUserPw());
			
			System.out.println("sql + +++++" + sql);

			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

			while (rs.next()) {
				cnt = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 담기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		// 최종 결과 반환
		return cnt;
	}

	@Override
	public Member selectBusinessMemberByUserNo(Connection conn, Member member) {
	
		String sql = "";
		sql += "SELECT * FROM member";
		sql += " WHERE 1=1";
		sql += "	AND userId = ? ";
		sql += "	AND userPw = ? ";

		// 결과 저장할 변수
		Member result = null;

		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체

//			ps.setInt(1, 1);
			ps.setString(1, member.getUserId());
			ps.setString(2, member.getUserPw());

			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

			while (rs.next()) {
				result = new Member();

				result.setUserNo( rs.getInt("userNo") );
				result.setUserKind( rs.getInt("userKind") );
				result.setUserId( rs.getString("userId") );
				result.setUserPw( rs.getString("userPw") );
				result.setUserName( rs.getString("userName") );
				result.setUserNick( rs.getString("userNick") );
				result.setUserPh( rs.getString("userPh") );
				result.setUserEm( rs.getString("userEm") );
				result.setUserGen( rs.getString("userGen") );
				result.setUserBirth( rs.getString("userBirth") );
				result.setJoinDate( rs.getDate("joinDate") );
				result.setBusinessNo( rs.getString("businessNo") );
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		// 최종 결과 반환
		return result;
	}

	@Override
	public int selectNextUserNo(Connection conn) {
		
		//SQL작성
		String sql = "";
		sql += "SELECT member_seq.nextval FROM dual";
		
		//결과 저장 변수
		int nextmemberFileNo = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				nextmemberFileNo = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//자원 해제
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		//결과 반환
		return nextmemberFileNo;
	}

	@Override
	public int insertBusiness(Connection conn, Member member) {
		
		//SQL 작성
		String sql = "";
		sql += "INSERT INTO member "
				+ "( userNo, userKind, userId, userPw, userName, userNick, userPh, userEm, userGen, userBirth, joinDate, businessNo )";
//		sql += " VALUES( member_seq.nextval, 2, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ? )";
		sql += " VALUES( ?, 2, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ? )";
		
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);	// SQL 수행 객체

//			ps.setInt(1, 2);
//			ps.setInt(1, member.getUserKind());
			int idx = 1;
			
			ps.setInt(idx++, member.getUserNo());
			ps.setString(idx++, member.getUserId());
			ps.setString(idx++, member.getUserPw());
			ps.setString(idx++, member.getUserName());
			ps.setString(idx++, member.getUserNick());
			ps.setString(idx++, member.getUserPh());
			ps.setString(idx++, member.getUserEm());
			ps.setString(idx++, member.getUserGen());
			ps.setString(idx++, member.getUserBirth());
			ps.setString(idx++, member.getBusinessNo());
			
			res = ps.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			//DB객체 닫기
			JDBCTemplate.close(ps);
		}		
		return res;
	}

	
	
	
	//----------MemberFileUpload--------------------------------------------------
	
	@Override
	public int insertParamFile(Connection conn, MemberFile memberFile) {
	
		System.out.println("회원파일:" + memberFile);
		
		String sql = "";
		sql += "INSERT INTO memberFile (fileNo, userNo, pharmacy, originName, storedName)";
		sql += " VALUES( memberFile_seq.nextval, ?, ?, ?, ?)";
		
		//수행 결과 변수
		int result = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, memberFile.getUserNo());
			ps.setString(2, memberFile.getPharmacy());
			ps.setString(3, memberFile.getOriginName());
			ps.setString(4, memberFile.getStoredName());

			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		//최종 수행 결과 반환
		return result;
	}
	

	@Override
	public List<MemberFile> selectAll(Connection conn) {
		
		String sql = "";
		sql += "SELECT fileNO, userNo, pharmacy, originName, storedName";
		sql += " ORDER BY fileNo DESC";
		
		List<MemberFile> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				MemberFile memberFile = new MemberFile();
				
				memberFile.setFileNo(rs.getInt("fileNo") );
				memberFile.setUserNo( rs.getInt("userNo") );
				memberFile.setPharmacy( rs.getString("pharmacy") );
				memberFile.setOriginName( rs.getString("originName") );
				memberFile.setStoredName( rs.getString("storedName") );
				
				list.add(memberFile);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회결과 반환
		return list;
	}

	

}
