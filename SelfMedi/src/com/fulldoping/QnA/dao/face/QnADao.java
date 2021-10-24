package com.fulldoping.QnA.dao.face;

import java.sql.Connection;
import java.util.List;

import com.fulldoping.QnA.dto.QnA;
import com.fulldoping.QnA.dto.QnAComments;
import com.fulldoping.QnA.dto.QnADeclare;
import com.fulldoping.QnA.dto.QnAFile;
import com.fulldoping.QnA.paging.Paging;

public interface QnADao {
	
	/**
	 * userno로 userid 조회
	 * 
	 */
	public String getUserId(Connection conn, int userno);
	
	/**
	 * Board테이블 전체 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return List<Board> - Board테이블 전체 조회 결과 리스트
	 */
	public List<QnA> selectAll(Connection conn);

	/**
	 * Board테이블 전체 조회
	 * 	페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @param conn - DB연결 객체
	 * @return List<Board> - Board테이블 전체 조회 결과 리스트
	 */
	public List<QnA> selectAll(Connection conn, Paging paging);
	
	/**
	 * 총 게시글 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - Board테이블 전체 행 수 조회 결과
	 */
	public int selectCntAll(Connection conn);
	
	/**
	 * 특정 게시글 조회
	 * 
	 * @param boardno - 조회할 boardno를 가진 객체
	 * @return Board - 조회된 결과 객체
	 */
	public QnA selectBoardByBoardno(Connection conn, QnA boardno);

	/**
	 * 조회된 게시글의 조회수 증가시키기
	 * 
	 * @param boardno - 조회된 게시글 번호를 가진 객체
	 */
	public int updateHit(Connection conn, QnA boardno);

	/**
	 * id를 이용해 nick을 조회한다
	 * 
	 * @param viewBoard - 조회할 id를 가진 객체
	 * @return String - 작성자 닉네임
	 */
	public String selectNickByUserid(Connection conn, QnA viewBoard);
	
	/**
	 * 게시글 입력
	 * 
	 * @param board - 삽입될 게시글 내용
	 */
	public int insert(Connection conn, QnA board);

	/**
	 * 게시글 수정 
	 * 
	 * @param board - 수정할 내용을 담은 객체
	 */
	public int update(Connection conn, QnA board);

	/**
	 * 게시글 삭제
	 * 
	 * @param board - 삭제할 게시글번호를 담은 객체
	 */
	public int delete(Connection conn, QnA board);
	
	/**
	 * 다음 게시글 번호 조회
	 * 
	 * 	게시글 테이블과 첨부파일 테이블에 입력될 공통 boardno값을 시퀀스를 통해 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @return 다음 게시글 번호
	 */
	public int selectNextBoardno(Connection conn);

	/**
	 * 첨부파일 입력
	 * 
	 * @param conn - DB연결 객체
	 * @param boardFile - 첨부파일 정보
	 * @return 삽입 결과
	 */
	public int selectinsertFile(Connection conn, QnAFile boardFile);

	/**
	 * 첨부파일 조회
	 * 
	 * @param connection - DB연결 객체
	 * @param viewBoard - 첨부파일을 조회할 게시글번호 객체
	 * @return BoardFile - 조회된 첨부파일
	 */
	public QnAFile File(Connection conn, QnA viewBoard);

	/**
	 * 게시글에 첨부된 파일 기록 삭제
	 * 
	 * @param board - 삭제할 게시글번호를 담은 객체
	 */
	public int deleteFile(Connection conn, QnA board);
	public int deleteFile(Connection conn, int boardNo);

	/**
	 * QnA댓글테이블 전체 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return List<Board> - QnA테이블 전체 조회 결과 리스트
	 */
	public List<QnAComments> selectAllComments(Connection conn);
	
	/**
	 * 게시글에 대한 QnA댓글테이블 전체 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return List<Board> - QnA테이블 전체 조회 결과 리스트
	 */
	public List<QnAComments> selectAllComments(Connection conn, int boardno);
	
	/**
	 * 다음 댓글 번호 조회
	 * 
	 * 	게시글 테이블과 첨부파일 테이블에 입력될 공통 commentno값을 시퀀스를 통해 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @return 다음 댓글 번호
	 */
	public int selectNextCommentno(Connection conn);
	
	/**
	 * 특정 댓글 조회
	 * 
	 * @param commentsno - 조회할 boardno를 가진 객체
	 * @return QnAComments - 조회된 결과 객체
	 */
	public QnAComments selectcommentBycommentno(Connection conn, QnAComments commentno);
	
	/**
	 * 댓글 입력
	 * 
	 * @param comments - 삽입될 댓글 내용
	 */
	public int commentsinsert(Connection conn, QnAComments comment);

	/**
	 * 댓글 수정 
	 * 
	 * @param comments - 수정할 댓글을 담은 객체
	 */
	public int commentsupdate(Connection conn, QnAComments comment);

	/**
	 * 댓글 삭제
	 * 
	 * @param comments - 삭제할 댓글번호를 담은 객체
	 */
	public int commentsdelete(Connection conn, QnAComments comment);
	
	/**
	 * 댓글 삭제
	 * 
	 * @param comments - 삭제할 게시판 번호를 담은 객체
	 */
	public int commentsdelete(Connection conn, QnA board);
	

	/**
	 * userNo로 userNick을 가져오는 기능
	 */
	public String getUserNick(Connection conn, Integer userNo);

	/**
	 * 신고 기능
	 * @param conn - DB연결 객체
	 * @param qnaDeclare - QnA신고
	 * @return
	 */
	public int declare(Connection conn, QnADeclare qnaDeclare);

	
}
