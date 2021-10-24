package com.fulldoping.QnA.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fulldoping.QnA.dao.face.QnADao;
import com.fulldoping.QnA.dto.QnA;
import com.fulldoping.QnA.dto.QnAComments;
import com.fulldoping.QnA.dto.QnADeclare;
import com.fulldoping.QnA.dto.QnAFile;
import com.fulldoping.QnA.paging.Paging;
import com.fulldoping.common.JDBCTemplate;

public class QnADaoImpl implements QnADao {
	
	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	public String getUserId(Connection conn, int userno) {
		System.out.println("GETUSERID 메소드 :" + userno);
		//SQL 작성
		String sql = "";
		sql += "SELECT userid FROM member"; 
		sql += " WHERE userno = ?";
		
		String userId = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userno);
			
			rs = ps.executeQuery();
			
			//조회 결과 처리
			while(rs.next()) {
				userId = rs.getString("userid");
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
	public List<QnA> selectAll(Connection conn) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM QNA";
		sql += " ORDER BY boardno DESC";
		
		//결과 저장할 List
		List<QnA> boardList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				QnA q = new QnA(); //결과값 저장 객체
				
				//결과값 한 행 처리
				q.setBoardNo( rs.getInt("boardno") );
				q.setUserNo(rs.getInt("userno"));
				q.setUserId( rs.getString("userid") );
				q.setUserNick( rs.getString("usernick") );
				q.setBoardTitle( rs.getString("boardtitle") );
				q.setBoardDate( rs.getDate("boarddate") );
				q.setBoardContent( rs.getString("boardcontent") );
				q.setDeclare(rs.getString("declare"));
				q.setHit( rs.getInt("hit") );
				
				//리스트에 결과값 저장
				boardList.add(q);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return boardList;
	}

	@Override
	public List<QnA> selectAll(Connection conn, Paging paging) {
		
		System.out.println(paging.getStartNo() + " " + paging.getEndNo());
		
		//SQL작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += "		SELECT";
		sql += "			boardno, userno, userid, usernick, boardtitle, boarddate";
		sql += "			, boardcontent, declare, hit";
		sql += "		FROM QNA";
		sql += "		ORDER BY boardno DESC";
		sql += "	) B";
		sql += " ) BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";

		//결과 저장할 List
		List<QnA> boardList = new ArrayList<>(); 
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				QnA q = new QnA(); //결과값 저장 객체
				
				//결과값 한 행 처리
				q.setBoardNo( rs.getInt("boardno") );
				q.setUserNo(rs.getInt("userno"));
				q.setUserId( rs.getString("userid") );
				q.setUserNick( rs.getString("usernick") );
				q.setBoardTitle( rs.getString("boardtitle") );
				q.setBoardDate( rs.getDate("boarddate") );
				q.setBoardContent( rs.getString("boardcontent") );
				q.setDeclare(rs.getString("declare"));
				q.setHit( rs.getInt("hit") );

				//리스트에 결과값 저장
				boardList.add(q);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return boardList;
	}

	@Override
	public int selectCntAll(Connection conn) {
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM qna";
		
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
	public QnA selectBoardByBoardno(Connection conn, QnA boardno) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM QnA";
		sql += " WHERE boardno = ?";
		
		//결과 저장할 Board객체
		QnA viewBoard = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, boardno.getBoardNo()); //조회할 게시글 번호 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				viewBoard = new QnA(); //결과값 저장 객체
				
				//결과값 한 행 처리
				viewBoard.setBoardNo( rs.getInt("boardno") );
				viewBoard.setUserNo(rs.getInt("userno"));
				viewBoard.setUserId( rs.getString("userid") );
				viewBoard.setUserNick( rs.getString("userNick") );
				viewBoard.setBoardTitle( rs.getString("boardtitle") );
				viewBoard.setBoardDate( rs.getDate("boarddate") );
				viewBoard.setBoardContent( rs.getString("boardcontent") );
				viewBoard.setDeclare(rs.getString("declare"));
				viewBoard.setHit( rs.getInt("hit") );
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return viewBoard;
	}

	@Override
	public int updateHit(Connection conn, QnA boardno) {
		//SQL 작성
		String sql = "";
		sql += "UPDATE QnA";
		sql += " SET hit = hit + 1";
		sql += " WHERE boardno = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, boardno.getBoardNo()); //조회할 게시글 번호 적용
			
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
	public String selectNickByUserid(Connection conn, QnA viewBoard) {
		//SQL 작성
		String sql = "";
		sql += "SELECT usernick FROM member";
		sql += " WHERE userid = ?";
		
		//결과 저장할 String 변수
		String usernick = null;
				
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setString(1, viewBoard.getUserId()); //조회할 id 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				usernick = rs.getString("usernick");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return usernick;
	}

	@Override
	public int insert(Connection conn, QnA board) {
		String sql = "";
		sql += "INSERT INTO QNA(BOARDNO, USERNO, USERID, USERNICK,BOARDTITLE, BOARDDATE, BOARDCONTENT, DECLARE, HIT)";
		sql += " VALUES (?, ?, ?, ?, ?, SYSDATE, ?, 'N', 0)";
				
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, board.getBoardNo());
			ps.setInt(2, board.getUserNo());
			ps.setString(3, board.getUserId());
			ps.setString(4, board.getUserNick());	
			ps.setString(5, board.getBoardTitle());
			ps.setString(6, board.getBoardContent());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int update(Connection conn, QnA board) {
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "UPDATE QnA";
		sql += " SET boardtitle = ?,";
		sql += " 	boardcontent = ?";
		sql += " WHERE boardno = ?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, board.getBoardTitle());
			ps.setString(2, board.getBoardContent());
			ps.setInt(3, board.getBoardNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int delete(Connection conn, QnA board) {
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
	public int selectNextBoardno(Connection conn) {
		String sql = "";
		sql += "SELECT qna_seq.nextval FROM dual";
		
		//결과 저장 변수
		int nextBoardno = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				nextBoardno = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return nextBoardno;
	}

	@Override
	public int selectinsertFile(Connection conn, QnAFile boardFile) {
		String sql = "";
		sql += "INSERT INTO qnafile( fileno, boardno, originname, storedname, filesize )";
		sql += " VALUES( qnafile_seq.nextval, ?, ?, ?, ? )";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, boardFile.getBoardNo());
			ps.setString(2, boardFile.getOriginName());
			ps.setString(3, boardFile.getStoredName());
			ps.setInt(4, boardFile.getFileSize());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public QnAFile File(Connection conn, QnA viewBoard) {
		String sql = "";
		sql += "SELECT * FROM QNAFILE"; 
		sql += " WHERE boardno = ?"; 
		sql += " ORDER BY FILENO";

		QnAFile boardFile = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, viewBoard.getBoardNo());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				boardFile = new QnAFile();
				
				boardFile.setFileNo( rs.getInt("fileno") );
				boardFile.setBoardNo( rs.getInt("boardno") );
				boardFile.setOriginName( rs.getString("originname") );
				boardFile.setStoredName( rs.getString("storedname") );
				boardFile.setFileSize( rs.getInt("filesize") );
				boardFile.setWriteDate( rs.getDate("writedate") );
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
				
		return boardFile;
	}

	@Override
	public int deleteFile(Connection conn, QnA board) {
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
	public int deleteFile(Connection conn, int boardNo) {
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
			ps.setInt(1, boardNo);

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public List<QnAComments> selectAllComments(Connection conn) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM QNACOMMENTS";
		sql += " ORDER BY boardno DESC";
		
		//결과 저장할 List
		List<QnAComments> commentList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				QnAComments q = new QnAComments(); //결과값 저장 객체
				
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
	public List<QnAComments> selectAllComments(Connection conn, int boardno) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM QNACOMMENTS";
		sql += " WHERE boardno = ?";
		sql += " ORDER BY commentno DESC";
		
		//결과 저장할 List
		List<QnAComments> commentList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, boardno);
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				QnAComments q = new QnAComments(); //결과값 저장 객체
				
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
	public int commentsinsert(Connection conn, QnAComments comment) {
		String sql = "";
		sql += "INSERT INTO QNACOMMENTS(COMMENTNO, BOARDNO, USERNO, USERNICK, COMMENTCONTENT, COMMENTDATE)";
		sql += " VALUES (?, ?, ?, ?,?, SYSDATE)";
				
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
		sql += "SELECT qnacomments_seq.nextval FROM dual";
		
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
	public QnAComments selectcommentBycommentno(Connection conn, QnAComments commentno) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM qnacomments";
		sql += " WHERE commentno = ?";
		
		//결과 저장할 Board객체
		QnAComments viewcomment = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, commentno.getCommentNo()); //조회할 게시글 번호 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				viewcomment = new QnAComments(); //결과값 저장 객체
				
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
	public int commentsupdate(Connection conn, QnAComments comment) {
		//다음 댓글 번호 조회 쿼리
		String sql = "";
		sql += "UPDATE QNACOMMENTS";
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
	public int commentsdelete(Connection conn, QnAComments comment) {
		//다음 댓글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE qnacomments";
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
	public int commentsdelete(Connection conn, QnA board) {
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE qnacomments";
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
	
	@Override
	public int declare(Connection conn, QnADeclare qnaDeclare) {
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "INSERT INTO QnADeclare(BOARDNO , USERNO, USERID , BOARDTITLE , BOARDCONTENT, REASON, USERNICK, HIT)";
		sql += " VALUES ( ? , ?, ?, ?, ?, ?, ?, ?)";
		
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, qnaDeclare.getBoardNo());
			ps.setInt(2, qnaDeclare.getUserNo());
			ps.setString(3, qnaDeclare.getUserId());
			ps.setString(4, qnaDeclare.getBoardTitle());
			ps.setString(5, qnaDeclare.getBoardContent());
			ps.setString(6, qnaDeclare.getReason());
			ps.setString(7, qnaDeclare.getUserNick());
			ps.setInt(8, qnaDeclare.getHit());
			
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}
	
}
