package com.fulldoping.admin.free.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fulldoping.admin.free.dao.face.AdFreeDao;
import com.fulldoping.admin.free.paging.AdFreePaging;
import com.fulldoping.common.JDBCTemplate;
import com.fulldoping.free.dto.Free;
import com.fulldoping.free.dto.FreeFile;

public class AdFreeDaoImpl implements AdFreeDao {

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public List<Free> selectAll(Connection conn, AdFreePaging adFreePaging) {
		
		//SQL작성
		String sql = "";
		sql += "SELECT * FROM ( ";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += "		SELECT";
		sql += "			boardno, userid, userNick, boardtitle";
		sql += "			, boarddate, hit";
		sql += "		FROM free";
		sql += "		ORDER BY boardno DESC";
		sql += "	) B";
		sql += " ) FREE";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		//결과 저장할 List
		List<Free> adfreeList = new ArrayList<>(); 
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, adFreePaging.getStartNo());
			ps.setInt(2, adFreePaging.getEndNo());
			
			rs = ps.executeQuery();
			
			
			while (rs.next()) {
				Free adfree = new Free();
				
				adfree.setBoardNo(rs.getInt("boardNo"));
				adfree.setUserId( rs.getString("userId"));
				adfree.setUserNick( rs.getString("userNick"));
				adfree.setBoardTitle( rs.getString("boardTitle"));
				adfree.setBoardDate( rs.getDate("boardDate"));
				adfree.setHit( rs.getInt("hit"));
				
				adfreeList.add(adfree);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return adfreeList;
	}
	
	@Override
	public int selectCntAll(Connection conn) {
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM free";
		
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
	public FreeFile selectFile(Connection conn, Free viewFree) {
		
		String sql = "";
		sql += "SELECT * FROM freeFile";
		sql += " WHERE boardNo = ?";
		sql += " ORDER BY fileNo";

		FreeFile freeFile = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, viewFree.getBoardNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				freeFile = new FreeFile();
				
				freeFile.setFileNo( rs.getInt("fileNo"));
				freeFile.setBoardNo( rs.getInt("boardNo"));
				freeFile.setOriginName( rs.getString("originName"));
				freeFile.setStoredName( rs.getString("storedName"));
				freeFile.setFileSize( rs.getInt("fileSize"));		
				freeFile.setWriteDate( rs.getDate("writeDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
				
		return freeFile;
	}
	
	@Override
	public Free selectFreeByBoardNo(Connection conn, Free boardNo) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM free";
		sql += " WHERE boardNo = ?";
		
		//결과 저장할 Free객체
		Free viewFree = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, boardNo.getBoardNo()); //조회할 게시글 번호 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				viewFree = new Free(); //결과값 저장 객체
				
				//결과값 한 행 처리
				viewFree.setBoardNo( rs.getInt("boardNo"));
				viewFree.setUserNo( rs.getInt("userNo"));
				viewFree.setUserId( rs.getString("userId"));
				viewFree.setBoardTitle( rs.getString("boardTitle"));
				viewFree.setBoardDate( rs.getDate("boardDate"));
				viewFree.setBoardContent( rs.getString("BoardContent"));
				viewFree.setDeclare( rs.getString("declare"));
				viewFree.setHit( rs.getInt("hit"));
				viewFree.setUserNick( rs.getString("userNick"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		System.out.println("TESTviewFREE" + viewFree);
		//최종 결과 반환
		return viewFree;
	}
	
	@Override
	public String selectuserNickByUserId(Connection conn, Free viewFree) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT userNick FROM member";
		sql += " WHERE userId = ?";
		
		//결과 저장할 String 변수
		String userNick = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setString(1, viewFree.getUserId()); //조회할 id 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				userNick = rs.getString("userNick");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		System.out.println("NickTEST" + userNick);
		//최종 결과 반환
		return userNick;
		
	}
	
	@Override
	public int updateHit(Connection conn, Free boardNo) {
		
		//SQL 작성
		String sql = "";
		sql += "UPDATE free";
		sql += " SET hit = hit + 1";
		sql += " WHERE boardNo = ?";	
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, boardNo.getBoardNo()); //조회할 게시글 번호 적용
			
			res = ps.executeUpdate(); //SQL 수행
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
