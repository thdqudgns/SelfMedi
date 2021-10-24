package com.fulldoping.free.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fulldoping.free.dto.Free;
import com.fulldoping.free.dto.FreeComments;
import com.fulldoping.free.dto.FreeFile;
import com.fulldoping.free.paging.FreePaging;

public interface FreeService {

	/**
	 * 페이징 처리 추가 된 게시글 전체 조회
	 * 
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<Free> - 게시글 전체 조회 결과 리스트
	 */
	public List<Free> getList(FreePaging paging);
	
	/**
	 * 페이징 객체 생성
	 * 
	 * 요청파라미터 curPage를 구한다
	 * Free테이블과 curPage값을 이용하여 Paging객체를 구하여 반환한다
	 * 
	 * @param req - 요청정보 객체
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public FreePaging getPaging(HttpServletRequest req);

	/**
	 * 요청파라미터 얻기
	 * 
	 * @param req - 요청정보객체
	 * @return Free - 전달파라미터 boardNo를 포함한 객체
	 */
	public Free getboardNo(HttpServletRequest req);

	/**
	 * 주어진boardNo를 이용하여 게시글을 조회한다
	 * 조회된 게시글의 조회수를 1 증가시킨다
	 * 
	 * @param boardNo - boardNo를 가지고 있는 객체
	 * @return Free - 조회된 게시글
	 */
	public Free view(Free boardNo);
	
	
	
	
	/**
	 * 게시글 작성
	 * 입력한 게시글 내용을 DB에 저장
	 * 
	 * 첨부파일을 함께 업로드 할 수 있도록 처리
	 * 
	 * @param req - 요청정보 객체(게시글 내용 + 첨부파일)
	 */
	public void write(HttpServletRequest req);
	
	/**
	 * 첨부파일 정보 조회
	 * 
	 * @param viewFree - 첨부파일과 연결된 게시글번호를 포함한 DTO객체
	 * @return FreeFile - 첨부파일 정보 DTO객체
	 */
	public FreeFile viewFile(Free viewFree);

	/**
	 * 게시글 수정
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void update(HttpServletRequest req);
	
	/**
	 * 게시글 삭제
	 * 
	 * @param free - 삭제할 게시글 번호를 가진 객체
	 */
	public void delete(Free free);

	/**
	 * Free 객체의 id를 이용한 닉네임 조회
	 * 
	 * @param viewFree - 조회할 게시글 정보
	 * @return String - 게시글 작성자의 닉네임
	 */
	public String getuserNick(Free viewFree);

	/**
	 * 게시글 신고 기능
	 * 
	 * @param req - 요청 정보 객체
	 */
	
	public void declare(HttpServletRequest req);

	public int commentInsert(HttpServletRequest req);

	public FreeComments getCommentno(HttpServletRequest req);

	public 	FreeComments getUpdateComment(HttpServletRequest req);

	public 	FreeComments getComment(int commentno);

	public 	void commentUpdate(HttpServletRequest req);

	public 	void commentDelete(FreeComments comment);

	public List<FreeComments> getCommentList(int boardNo);


	






	

}
