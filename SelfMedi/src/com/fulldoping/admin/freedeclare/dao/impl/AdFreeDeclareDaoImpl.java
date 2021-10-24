package com.fulldoping.admin.freedeclare.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fulldoping.admin.freedeclare.dao.face.AdFreeDeclareDao;
import com.fulldoping.admin.freedeclare.paging.FreeDeclarePaging;
import com.fulldoping.common.JDBCTemplate;
import com.fulldoping.free.dto.FreeDeclare;
import com.fulldoping.free.dto.FreeFile;

public class AdFreeDeclareDaoImpl implements AdFreeDeclareDao{

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List<FreeDeclare> selectAll(Connection conn, FreeDeclarePaging freeDeclarePaging) {
		
		//SQL작성
		String sql = "";
		sql += "SELECT * FROM ( ";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += "		SELECT";
		sql += "			boardNo, userId, userNick, boardTitle";
		sql += "			, boardDate, reason, hit";
		sql += "		FROM freeDeclare";
		sql += "		ORDER BY boardNo DESC";
		sql += "	) B";
		sql += " ) freeDeclare";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		//결과 저장할 List
		List<FreeDeclare> freeDeclareList = new ArrayList<>(); 
		
		try {
		
			ps = conn.prepareStatement(sql);
			ps.setInt(1, freeDeclarePaging.getStartNo());
			ps.setInt(2, freeDeclarePaging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				FreeDeclare freeDeclare = new FreeDeclare();
				
				freeDeclare.setBoardNo( rs.getInt("boardNo"));
				freeDeclare.setUserId( rs.getString("userId"));
				freeDeclare.setUserNick( rs.getString("userNick"));
				freeDeclare.setBoardTitle( rs.getString("boardTitle"));
				freeDeclare.setBoardDate( rs.getDate("boardDate"));
				freeDeclare.setHit( rs.getInt("hit"));
				
				//리스트에 결과값 저장
				freeDeclareList.add(freeDeclare);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return freeDeclareList;
	}
	
	@Override
	public int selectCntAll(Connection conn) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM freeDeclare";
		
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
	public FreeFile selectFile(Connection conn, FreeDeclare viewFreeDeclare) {
		String sql = "";
		sql += "SELECT * FROM freeFile";
		sql += " WHERE boardNo = ?";
		sql += " ORDER BY fileNo";
	
		FreeFile freeFile = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, viewFreeDeclare.getBoardNo());
			
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
	public FreeDeclare selectFreeByBoardNo(Connection conn, FreeDeclare boardNo) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM freeDeclare";
		sql += " WHERE boardNo = ?";
		
		//결과 저장할 Free객체
		FreeDeclare viewFreeDeclare = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, boardNo.getBoardNo()); //조회할 게시글 번호 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				viewFreeDeclare = new FreeDeclare(); //결과값 저장 객체
				
				//결과값 한 행 처리
				viewFreeDeclare.setBoardNo( rs.getInt("boardNo"));
				viewFreeDeclare.setUserNo( rs.getInt("userNo"));
				viewFreeDeclare.setUserId( rs.getString("userId"));
				viewFreeDeclare.setBoardTitle( rs.getString("boardTitle"));
				viewFreeDeclare.setBoardDate( rs.getDate("boardDate"));
				viewFreeDeclare.setBoardContent( rs.getString("BoardContent"));
				viewFreeDeclare.setDeclare( rs.getString("declare"));
				viewFreeDeclare.setHit( rs.getInt("hit"));
				viewFreeDeclare.setReason( rs.getString("reason"));
				viewFreeDeclare.setUserNick( rs.getString("userNick"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		

		//최종 결과 반환
		return viewFreeDeclare;
	}
	
	
//	@Override
//	public String selectuserNickByUserId(Connection conn, FreeDeclare viewFreeDeclare) {
//		
//		//SQL 작성
//		String sql = "";
//		sql += "SELECT userNick FROM member";
//		sql += " WHERE userId = ?";
//		
//		//결과 저장할 String 변수
//		String userNick = null;
//		
//		try {
//			ps = conn.prepareStatement(sql); //SQL수행 객체
//			ps.setString(1, viewFreeDeclare.getUserId()); //조회할 id 적용
//			
//			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
//			
//			//조회 결과 처리
//			while(rs.next()) {
//				userNick = rs.getString("userNick");
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			//DB객체 닫기
//			JDBCTemplate.close(rs);
//			JDBCTemplate.close(ps);
//		}
//		
//		//최종 결과 반환
//		return userNick;
//		
//	}
	
	@Override
	public int updateHit(Connection conn, FreeDeclare boardNo) {
		//SQL 작성
		String sql = "";
		sql += "UPDATE freeDeclare";
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
	
	@Override
	public int deleteFile(Connection conn, FreeDeclare freeDeclare) {
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE freefile";
		sql += " WHERE boardno = ?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, freeDeclare.getBoardNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int delete(Connection conn, FreeDeclare freeDeclare) {
		
		System.out.println("TEST FreeDeclare " + freeDeclare);
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE FREEDECLARE";
		sql += " WHERE boardNo = ? ";

		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, freeDeclare.getBoardNo());
			
			res = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		
		return res;
	}
	

	@Override
	public int deletecomments(Connection conn, FreeDeclare freeDeclare) {
		
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE freecomments";
		sql += " WHERE boardNo = ?";
	
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, freeDeclare.getBoardNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		
		return res;
	}
	@Override
	public int deletefree(Connection conn, FreeDeclare freeDeclare) {
		
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE FREE";
		sql += " WHERE boardNo = ?";
	
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, freeDeclare.getBoardNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		
		return res;
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
