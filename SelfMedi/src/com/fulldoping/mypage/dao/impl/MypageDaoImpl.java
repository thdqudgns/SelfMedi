package com.fulldoping.mypage.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fulldoping.QnA.dto.QnA;
import com.fulldoping.common.JDBCTemplate;
import com.fulldoping.free.dto.Free;
import com.fulldoping.member.dto.Member;
import com.fulldoping.mypage.dao.face.MypageDao;
import com.fulldoping.notice.dto.Notice;
import com.fulldoping.selftest.dto.SelfTest;


public class MypageDaoImpl implements MypageDao {
	
	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public Member getMemberInformation(Connection conn, int memberNo) {
		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM member";
		sql += " WHERE userno=?";

		// 결과 저장할 변수
		Member result = null;

		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체

			ps.setInt(1, memberNo);

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
				result.setUserBirth((String) rs.getString("userBirth") );
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
	public List<SelfTest> getList(Connection connection, int userNo) {
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT * FROM selftest";
		sql += " WHERE userno = ?";
		sql += " ORDER BY selftestno DESC";
		
		List<SelfTest> list = new ArrayList<>();
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userNo);
			
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
		
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps); //자원 해제
		}
		
		return res;
	}
	
	@Override
	public List<Notice> getNoticeInformation(Connection conn, int memberNo) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM notice";
		sql += " WHERE userno = ?";
		
		//결과 저장할 List
		List<Notice> noticeList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, memberNo); //조회할 게시글 번호 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				Notice notice = new Notice();
				
				//결과값 한 행 처리
				notice.setBoardNo( rs.getInt("boardNo"));
				notice.setUserNo( rs.getInt("userNo"));
				notice.setUserId( rs.getString("userId"));
				notice.setUserNick( rs.getString("userNick"));
				notice.setBoardTitle( rs.getString("boardTitle"));
				notice.setBoardDate( rs.getDate("boardDate"));
				notice.setBoardContent( rs.getString("BoardContent"));
				notice.setDeclare( rs.getString("declare"));
				notice.setHit( rs.getInt("hit"));
				
				//리스트에 결과값 저장
				noticeList.add(notice);	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return noticeList;
	}
	
	@Override
	public List<QnA> getQnAInformation(Connection conn, int memberNo) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM QNA";
		sql += " WHERE userno = ?";
		
		//결과 저장할 List
		List<QnA> qnaList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, memberNo); //조회할 게시글 번호 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				QnA qna = new QnA();
				
				//결과값 한 행 처리
				qna.setBoardNo( rs.getInt("boardNo"));
				qna.setUserNo( rs.getInt("userNo"));
				qna.setUserId( rs.getString("userId"));
				qna.setUserNick( rs.getString("userNick"));
				qna.setBoardTitle( rs.getString("boardTitle"));
				qna.setBoardDate( rs.getDate("boardDate"));
				qna.setBoardContent( rs.getString("BoardContent"));
				qna.setDeclare( rs.getString("declare"));
				qna.setHit( rs.getInt("hit"));
				
				//리스트에 결과값 저장
				qnaList.add(qna);	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return qnaList;
	}
	
	@Override
	public List<Free> getFreeInformation(Connection conn, int memberNo) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM FREE";
		sql += " WHERE userno = ?";
		
		//결과 저장할 List
		List<Free> freeList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, memberNo); //조회할 게시글 번호 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				Free free = new Free();
				
				//결과값 한 행 처리
				free.setBoardNo( rs.getInt("boardNo"));
				free.setUserNo( rs.getInt("userNo"));
				free.setUserId( rs.getString("userId"));
				free.setUserNick( rs.getString("userNick"));
				free.setBoardTitle( rs.getString("boardTitle"));
				free.setBoardDate( rs.getDate("boardDate"));
				free.setBoardContent( rs.getString("BoardContent"));
				free.setDeclare( rs.getString("declare"));
				free.setHit( rs.getInt("hit"));
				
				//리스트에 결과값 저장
				freeList.add(free);	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return freeList;
	}
	
	@Override
	public int updateNickInformation(Connection conn, Member member) {
		String sql = "";
		sql += "UPDATE MEMBER";
		sql += " SET USERNICK=?"; 
		sql += " WHERE USERNO=?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getUserNick());
			ps.setInt(2, member.getUserNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int updatePwInformation(Connection conn, Member member) {
		String sql = "";
		sql += "UPDATE MEMBER";
		sql += " SET USERPW=?"; 
		sql += " WHERE USERNO=?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getUserPw());
			ps.setInt(2, member.getUserNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}	
		return res;
	}
	
	@Override
	public int updateEmInformation(Connection conn, Member member) {
		String sql = "";
		sql += "UPDATE MEMBER";
		sql += " SET USEREM=?"; 
		sql += " WHERE USERNO=?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getUserEm());
			ps.setInt(2, member.getUserNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}	
		
		return res;
	}
	
	@Override
	public int updatePhInformation(Connection conn, Member member) {
		String sql = "";
		sql += "UPDATE MEMBER";
		sql += " SET USERPH=?"; 
		sql += " WHERE USERNO=?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getUserPh());
			ps.setInt(2, member.getUserNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}	
		
		return res;
	}
}
