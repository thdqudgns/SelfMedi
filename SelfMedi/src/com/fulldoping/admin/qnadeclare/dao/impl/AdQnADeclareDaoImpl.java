package com.fulldoping.admin.qnadeclare.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fulldoping.QnA.dto.QnADeclare;
import com.fulldoping.QnA.dto.QnAFile;
import com.fulldoping.admin.qnadeclare.dao.face.AdQnADeclareDao;
import com.fulldoping.admin.qnadeclare.paging.QnADeclarePaging;
import com.fulldoping.common.JDBCTemplate;

public class AdQnADeclareDaoImpl implements AdQnADeclareDao{

	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public List<QnADeclare> selectAll(Connection conn, QnADeclarePaging qnaDeclarePaging) {
		
		//SQL작성
		String sql = "";
		sql += "SELECT * FROM ( ";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += "		SELECT";
		sql += "			boardNo, userNick, boardTitle";
		sql += "			, boardDate, reason, hit";
		sql += "		FROM QnADeclare";
		sql += "		ORDER BY boardNo DESC";
		sql += "	) B";
		sql += " ) QnADeclare";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		//결과 저장할 List
		List<QnADeclare> qnaDeclareList = new ArrayList<>(); 
		
		try {
		
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qnaDeclarePaging.getStartNo());
			ps.setInt(2, qnaDeclarePaging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				QnADeclare qnaDeclare = new QnADeclare();
				
				qnaDeclare.setBoardNo( rs.getInt("boardNo"));
				qnaDeclare.setUserNick( rs.getString("userNick"));
				qnaDeclare.setBoardTitle( rs.getString("boardTitle"));
				qnaDeclare.setBoardDate( rs.getDate("boardDate"));
				qnaDeclare.setHit( rs.getInt("hit"));
				
				//리스트에 결과값 저장
				qnaDeclareList.add(qnaDeclare);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		System.out.println("testsadasdasdasdsa" + qnaDeclareList);
		return qnaDeclareList;
	}
	
	@Override
	public int selectCntAll(Connection conn) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM QnADeclare";
		
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
	public QnAFile selectFile(Connection conn, QnADeclare viewQnaDeclare) {
		
		String sql = "";
		sql += "SELECT * FROM qnaFile";
		sql += " WHERE boardNo = ?";
		sql += " ORDER BY fileNo";
	
		QnAFile qnaFile = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, viewQnaDeclare.getBoardNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				qnaFile = new QnAFile();
				
				qnaFile.setFileNo( rs.getInt("fileNo"));
				qnaFile.setBoardNo( rs.getInt("boardNo"));
				qnaFile.setOriginName( rs.getString("originName"));
				qnaFile.setStoredName( rs.getString("storedName"));
				qnaFile.setFileSize( rs.getInt("fileSize"));		
				qnaFile.setWriteDate( rs.getDate("writeDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
				
		return qnaFile;
	}
	
	@Override
	public QnADeclare selectQnAByBoardNo(Connection conn, QnADeclare boardNo) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM qnaDeclare";
		sql += " WHERE boardNo = ?";
		
		//결과 저장할 Free객체
		QnADeclare viewQnaDeclare = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, boardNo.getBoardNo()); //조회할 게시글 번호 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				viewQnaDeclare = new QnADeclare(); //결과값 저장 객체
				
				//결과값 한 행 처리
				viewQnaDeclare.setBoardNo( rs.getInt("boardNo"));
				viewQnaDeclare.setUserNo( rs.getInt("userNo"));
				viewQnaDeclare.setUserId( rs.getString("userId"));
				viewQnaDeclare.setBoardTitle( rs.getString("boardTitle"));
				viewQnaDeclare.setBoardDate( rs.getDate("boardDate"));
				viewQnaDeclare.setBoardContent( rs.getString("BoardContent"));
				viewQnaDeclare.setDeclare( rs.getString("declare"));
				viewQnaDeclare.setHit( rs.getInt("hit"));
				viewQnaDeclare.setReason( rs.getString("reason"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		

		//최종 결과 반환
		return viewQnaDeclare;
	}
	
	@Override
	public String selectuserNickByUserId(Connection conn, QnADeclare viewQnaDeclare) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT userNick FROM member";
		sql += " WHERE userId = ?";
		
		//결과 저장할 String 변수
		String userNick = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setString(1, viewQnaDeclare.getUserId()); //조회할 id 적용
			
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
		
		//최종 결과 반환
		return userNick;
		
	}
	
	@Override
	public int updateHit(Connection conn, QnADeclare boardNo) {
		//SQL 작성
		String sql = "";
		sql += "UPDATE qnaDeclare";
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
	public int deleteFile(Connection conn, QnADeclare qnaDeclare) {
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE qnafile";
		sql += " WHERE boardno = ?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qnaDeclare.getBoardNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int deletedeclare(Connection conn, QnADeclare qnaDeclare) {
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE QNADECLARE";
		sql += " WHERE boardNo = ? ";

		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qnaDeclare.getBoardNo());
			
			res = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		
		return res;
	}
	
	
	@Override
	public int deleteqna(Connection conn, QnADeclare qnaDeclare) {
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE QNA";
		sql += " WHERE boardNo = ?";
	
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qnaDeclare.getBoardNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		
		return res;
	}
	
	@Override
	public int deletecomment(Connection conn, QnADeclare qnaDeclare) {
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE QnAComments";
		sql += " WHERE boardNo = ?";
	
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qnaDeclare.getBoardNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		
		return res;
	}
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
