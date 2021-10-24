package com.fulldoping.selftest.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fulldoping.selftest.dao.face.SelfTestDao;
import com.fulldoping.selftest.dto.SelfTest;

import common.JDBCTemplate;

public class SelfTestDaoImpl implements SelfTestDao {
	
	private PreparedStatement ps = null;

	@Override
	public int insert(Connection conn, SelfTest selftest) {
		
		String sql = "";
		sql += "INSERT INTO selftest";
		sql += " (selftestno, userno, username, userage, usergender";
		sql += " , question01, question02, question03, question04, question05, question06, question07)";
		sql += " VALUES (selftest_seq.nextval, ?, ?, ?, ?";
		sql += " , ?, ?, ?, ?, ?, ?, ?)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			//DTO의 data들을 DB table에 저장하기
			ps.setInt(1, selftest.getUserNo());
			ps.setString(2, selftest.getUserName());
			ps.setString(3, selftest.getUserAge());
			ps.setString(4, selftest.getUserGender());
			
			ps.setString(5, selftest.getQuestion01());
			ps.setString(6, selftest.getQuestion02());
			ps.setString(7, selftest.getQuestion03());
			ps.setString(8, selftest.getQuestion04());
			ps.setString(9, selftest.getQuestion05());
			ps.setString(10, selftest.getQuestion06());
			ps.setString(11, selftest.getQuestion07());
			
			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps); //자원 해제
		}
		
		return res;
	} //insert END

//	@Override
//	public int getUserno(Connection connection) {
//		
//		ResultSet rs = null;
//		
//		String sql = "";
//		sql += "SELECT userno FROM selftest";
//		
//		
//		return 0;
//	}
	
	@Override
	public List<SelfTest> getList(Connection conn, int userno) {
		
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT * FROM selftest";
		sql += " WHERE userno = ?";
		sql += " ORDER BY selftestno DESC";
		
		List<SelfTest> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userno);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				SelfTest selftest = new SelfTest();
				
				selftest.setSelftestNo(rs.getInt("selftestno"));
				selftest.setUserNo(rs.getInt("userno"));
				selftest.setUserName(rs.getString("username"));
				selftest.setUserAge(rs.getString("userage"));
				selftest.setUserGender(rs.getString("usergender"));
				
				selftest.setQuestion01(rs.getString("question01"));
				selftest.setQuestion02(rs.getString("question02"));
				selftest.setQuestion03(rs.getString("question03"));
				selftest.setQuestion04(rs.getString("question04"));
				selftest.setQuestion05(rs.getString("question05"));
				selftest.setQuestion06(rs.getString("question06"));
				selftest.setQuestion07(rs.getString("question07"));
				
				list.add(selftest);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return list;
	} // getList END

	@Override
	public SelfTest viewByTestno(Connection conn, SelfTest selftestno) {
		
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT * FROM selftest WHERE selftestno = ?";
		
		SelfTest viewSelfTest = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, selftestno.getSelftestNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				viewSelfTest = new SelfTest();
				
				viewSelfTest.setSelftestNo(rs.getInt("selftestno"));
				viewSelfTest.setUserNo(rs.getInt("userno"));
				viewSelfTest.setUserName(rs.getString("username"));
				viewSelfTest.setUserAge(rs.getString("userage"));
				viewSelfTest.setUserGender(rs.getString("usergender"));
				viewSelfTest.setQuestion01(rs.getString("question01"));
				viewSelfTest.setQuestion02(rs.getString("question02"));
				viewSelfTest.setQuestion03(rs.getString("question03"));
				viewSelfTest.setQuestion04(rs.getString("question04"));
				viewSelfTest.setQuestion05(rs.getString("question05"));
				viewSelfTest.setQuestion06(rs.getString("question06"));
				viewSelfTest.setQuestion07(rs.getString("question07"));
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		return viewSelfTest;
	}

	@Override
	public int delete(Connection conn, SelfTest selftestno) {
		
		String sql = "";
		sql += "DELETE selftest WHERE selftestno = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, selftestno.getSelftestNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps); //자원 해제
		}
		
		return res;
	}

	

}



































