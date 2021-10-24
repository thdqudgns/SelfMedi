package com.fulldoping.free.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fulldoping.common.JDBCTemplate;
import com.fulldoping.free.dao.face.FreeDao;
import com.fulldoping.free.dto.Free;
import com.fulldoping.free.dto.FreeComments;
import com.fulldoping.free.dto.FreeDeclare;
import com.fulldoping.free.dto.FreeFile;
import com.fulldoping.free.paging.FreePaging;

public class FreeDaoImpl implements FreeDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public String getUserId(Connection conn, int userNo) {
		
		//SQL작성
		String sql = "";
		sql += "SELECT userId FROM member"; 
		sql += " WHERE userNo = ?";

		String userId = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userNo);
			
			rs = ps.executeQuery();
			
			//조회 결과 처리
			while (rs.next()) {
				userId = rs.getNString("userId");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps); 
		}
		
		return userId;
	}

	@Override
	public List<Free> selectAll(Connection conn, FreePaging paging) {
		
		//SQL작성
		String sql = "";
		sql += "SELECT * FROM ( ";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += "		SELECT";
		sql += "			boardNo, userId, userNick, boardTitle";
		sql += "			, boardDate, hit";
		sql += "		FROM free";
		sql += "		ORDER BY boardNo DESC";
		sql += "	) B";
		sql += " ) FREE";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		//결과 저장할 List
		List<Free> freeList = new ArrayList<>(); 
		
		try {
		
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Free free = new Free();
				
				free.setBoardNo( rs.getInt("boardNo"));
				free.setUserId( rs.getString("userId"));
				free.setUserNick( rs.getString("userNick"));
				free.setBoardTitle( rs.getString("boardTitle"));
				free.setBoardDate( rs.getDate("boardDate"));
				free.setHit( rs.getInt("hit"));
				
				//리스트에 결과값 저장
				freeList.add(free);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return freeList;
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
				viewFree.setUserNick( rs.getString("userNick"));
				viewFree.setBoardTitle( rs.getString("boardTitle"));
				viewFree.setBoardDate( rs.getDate("boardDate"));
				viewFree.setBoardContent( rs.getString("BoardContent"));
				viewFree.setDeclare( rs.getString("declare"));
				viewFree.setHit( rs.getInt("hit"));
				
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
	public int insert(Connection conn, Free free) {
		
		System.out.println("TESTFREE : "+ free);
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "INSERT INTO free(BOARDNO , USERNO, USERID , BOARDTITLE , BOARDCONTENT,  HIT, userNick)";
		sql += " VALUES (?, ?, ?, ?, ?, 0, ?)";
		
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, free.getBoardNo());
			ps.setInt(2, free.getUserNo());
			ps.setString(3, free.getUserId());
			ps.setString(4, free.getBoardTitle());
			ps.setString(5, free.getBoardContent());
			ps.setString(6, free.getUserNick());
			
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int selectNextBoardNo(Connection conn) {
		
		String sql = "";
		sql += "SELECT free_seq.nextval FROM dual";
		
		//결과 저장 변수
		int nextBoardNo = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				nextBoardNo = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return nextBoardNo;
	}
	
	@Override
	public int insertFile(Connection conn, FreeFile freeFile) {
		
		String sql = "";
		sql += "INSERT INTO freefile( fileNo, boardNo, originName, storedName, fileSize )";
		sql += " VALUES( freefile_seq.nextval, ?, ?, ?, ? )";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, freeFile.getBoardNo());
			ps.setString(2, freeFile.getOriginName());
			ps.setString(3, freeFile.getStoredName());
			ps.setInt(4, freeFile.getFileSize());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
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
	
//------------------------------------------------------------여기까지 구현 완료
	
	@Override
	public int update(Connection conn, Free free) {
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "UPDATE free";
		sql += " SET boardTitle = ?,";
		sql += " 	boardContent = ?";
		sql += " WHERE boardNo = ?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, free.getBoardTitle());
			ps.setString(2, free.getBoardContent());
			ps.setInt(3, free.getBoardNo());

			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		System.out.println("free" + res);
		return res;
	}

	@Override
	public int delete(Connection conn, Free free) {
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE free";
		sql += " WHERE boardNo= ?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		System.out.println("TESTRES: "+ free);
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, free.getBoardNo());
			System.out.println("TESTQWER: "+ free.getBoardNo());
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int deleteFile(Connection conn, Free free) {
		
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
			ps.setInt(1, free.getBoardNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int declare(Connection conn, FreeDeclare freeDeclare) {
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "INSERT INTO freeDeclare(BOARDNO , USERNO, USERID , BOARDTITLE , BOARDCONTENT, REASON, USERNICK, HIT)";
		sql += " VALUES ( ? , ?, ?, ?, ?, ?, ?, ?)";
		
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, freeDeclare.getBoardNo());
			ps.setInt(2, freeDeclare.getUserNo());
			ps.setString(3, freeDeclare.getUserId());
			ps.setString(4, freeDeclare.getBoardTitle());
			ps.setString(5, freeDeclare.getBoardContent());
			ps.setString(6, freeDeclare.getReason());
			ps.setString(7, freeDeclare.getUserNick());
			ps.setInt(8, freeDeclare.getHit());
			
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public List<FreeComments> selectAllComments(Connection conn) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM FREECOMMENTS";
		sql += " ORDER BY boardno DESC";
		
		//결과 저장할 List
		List<FreeComments> commentList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				FreeComments q = new FreeComments(); //결과값 저장 객체
				
				//결과값 한 행 처리
				q.setCommentNo(rs.getInt("commentno"));
				q.setBoardNo( rs.getInt("boardno") );
				q.setUserNo(rs.getInt("userNo"));
				q.setUserNick(rs.getString("userNick"));
				q.setCommentContent(rs.getString("commentcontent"));
				q.setCommentDate(rs.getDate("commentdate"));
				
				//리스트에 결과값 저장
				commentList.add(q);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return commentList;
	}
	
	@Override
	public List<FreeComments> selectAllComments(Connection conn, int boardno) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM FREECOMMENTS";
		sql += " WHERE boardno = ?";
		sql += " ORDER BY commentno DESC";
		
		//결과 저장할 List
		List<FreeComments> commentList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, boardno);
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				FreeComments q = new FreeComments(); //결과값 저장 객체
				
				//결과값 한 행 처리
				q.setCommentNo(rs.getInt("commentno"));
				q.setBoardNo( rs.getInt("boardno") );
				q.setUserNo(rs.getInt("userNo"));
				q.setUserNick(rs.getString("userNick"));
				q.setCommentContent(rs.getString("commentcontent"));
				q.setCommentDate(rs.getDate("commentdate"));

				//리스트에 결과값 저장
				commentList.add(q);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return commentList;
	}
	
	@Override
	public int commentsinsert(Connection conn, FreeComments comment) {
		String sql = "";
		sql += "INSERT INTO FREECOMMENTS(COMMENTNO, BOARDNO, USERNO, USERNICK, COMMENTCONTENT, COMMENTDATE)";
		sql += " VALUES (?, ?, ?, ?, ?, SYSDATE)";
				
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, comment.getCommentNo());
			ps.setInt(2, comment.getBoardNo());
			ps.setInt(3, comment.getUserNo());
			ps.setString(4, comment.getUserNick());
			ps.setString(5, comment.getCommentContent());
			

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int selectNextCommentno(Connection conn) {
		String sql = "";
		sql += "SELECT freecomments_seq.nextval FROM dual";
		
		//결과 저장 변수
		int nextCommentno = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				nextCommentno = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return nextCommentno;
	}
	
	@Override
	public FreeComments selectcommentBycommentno(Connection conn, FreeComments commentno) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM freecomments";
		sql += " WHERE commentno = ?";
		
		//결과 저장할 Board객체
		FreeComments viewcomment = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, commentno.getCommentNo()); //조회할 게시글 번호 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				viewcomment = new FreeComments(); //결과값 저장 객체
				
				//결과값 한 행 처리
				//결과값 한 행 처리
				viewcomment.setCommentNo( rs.getInt("commentno") );
				viewcomment.setBoardNo( rs.getInt("boardno") );
				viewcomment.setUserNo(rs.getInt("userNo"));
				viewcomment.setUserNick(rs.getString("userNick"));
				viewcomment.setCommentContent(rs.getString("commentcontent"));
				viewcomment.setCommentDate(rs.getDate("commentdate"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return viewcomment;
	}
	
	@Override
	public int commentsupdate(Connection conn, FreeComments comment) {
		//다음 댓글 번호 조회 쿼리
		String sql = "";
		sql += "UPDATE FreeCOMMENTS";
		sql += " SET COMMENTCONTENT=?, COMMENTDATE=SYSDATE"; 
		sql += " WHERE COMMENTNO=?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, comment.getCommentContent());
			ps.setInt(2, comment.getCommentNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int commentsdelete(Connection conn, FreeComments comment) {
		//다음 댓글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE freecomments";
		sql += " WHERE commentno = ?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, comment.getCommentNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int commentsdelete(Connection conn, Free board) {
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE freecomments";
		sql += " WHERE boardno = ?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board.getBoardNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public String getUserNick(Connection conn, Integer userNo) {
		//SQL 작성
		String sql = "";
		sql += "SELECT usernick FROM member"; 
		sql += " WHERE userno = ?";
		
		String userNick = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userNo);
			
			rs = ps.executeQuery();
			
			//조회 결과 처리
			while(rs.next()) {
				userNick = rs.getString("usernick");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return userNick;
	}
}

