package com.fulldoping.admin.qna.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fulldoping.QnA.dto.QnA;
import com.fulldoping.QnA.dto.QnAFile;
import com.fulldoping.admin.qna.dao.face.AdQnaDao;
import com.fulldoping.admin.qna.paging.AdQnaPaging;
import com.fulldoping.common.JDBCTemplate;


public class AdQnaDaoImpl implements AdQnaDao {

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public List<QnA> selectAll(Connection conn, AdQnaPaging adQnaPaging) {
		
		//SQL작성
		String sql = "";
		sql += "SELECT * FROM ( ";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += "		SELECT";
		sql += "			boardNo, userId, userNick, boardTitle";
		sql += "			, boardDate, hit";
		sql += "		FROM QnA";
		sql += "		ORDER BY boardNo DESC";
		sql += "	)B";
		sql += " )QnA";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		//결과 저장할 List
		List<QnA> adQnaList = new ArrayList<>(); 
		
		try {
		
			ps = conn.prepareStatement(sql);
			ps.setInt(1, adQnaPaging.getStartNo());
			ps.setInt(2, adQnaPaging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				QnA adQna = new QnA();
				
				adQna.setBoardNo( rs.getInt("boardNo"));
				adQna.setUserId( rs.getString("userId"));
				adQna.setUserNick( rs.getString("userNick"));
				adQna.setBoardTitle( rs.getString("boardTitle"));
				adQna.setBoardDate( rs.getDate("boardDate"));
				adQna.setHit( rs.getInt("hit"));
				
				//리스트에 결과값 저장
				adQnaList.add(adQna);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return adQnaList;
	}
	
	@Override
	public int selectCntAll(Connection conn) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM QnA";
		
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
	public QnAFile selectFile(Connection conn, QnA viewQna) {
		
		System.out.println( "rrrrrrrrrrrrrrrrrrr" + viewQna);
		
		String sql = "";
		sql += "SELECT * FROM QnAFile";
		sql += " WHERE boardNo = ?";
		sql += " ORDER BY fileNo";
	
		QnAFile qnaFile = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, viewQna.getBoardNo());
			
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
	public QnA selectFreeByBoardNo(Connection conn, QnA boardNo) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM QnA";
		sql += " WHERE boardNo = ?";
		
		//결과 저장할 객체
		QnA adQna = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, boardNo.getBoardNo()); //조회할 게시글 번호 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				adQna = new QnA(); //결과값 저장 객체
				
				//결과값 한 행 처리
				adQna.setBoardNo( rs.getInt("boardNo"));
				adQna.setUserNo( rs.getInt("userNo"));
				adQna.setUserId( rs.getString("userId"));
				adQna.setBoardTitle( rs.getString("boardTitle"));
				adQna.setBoardDate( rs.getDate("boardDate"));
				adQna.setBoardContent( rs.getString("BoardContent"));
				adQna.setDeclare( rs.getString("declare"));
				adQna.setHit( rs.getInt("hit"));
				adQna.setUserNick( rs.getString("userNick"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		

		//최종 결과 반환
		return adQna;
	}
	@Override
	public int updateHit(Connection conn, QnA boardNo) {
		
		//SQL 작성
		String sql = "";
		sql += "UPDATE QnA";
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
	public int commentsdelete(Connection conn, QnA qna) {
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE QnAComments";
		sql += " WHERE boardno = ?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qna.getBoardNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;

	}
	@Override
	public int delete(Connection conn, QnA qna) {
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE QnA";
		sql += " WHERE boardno = ?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qna.getBoardNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;

	}
	@Override
	public int deletedeclare(Connection conn, QnA qna) {
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE QnAdeclare";
		sql += " WHERE boardno = ?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qna.getBoardNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;

	}
	@Override
	public int deleteFile(Connection conn, QnA qna) {
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
			ps.setInt(1, qna.getBoardNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
