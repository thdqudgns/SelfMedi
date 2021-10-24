package com.fulldoping.admin.free.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fulldoping.admin.free.paging.AdFreePaging;
import com.fulldoping.free.dto.Free;
import com.fulldoping.free.dto.FreeFile;

public interface AdFreeService {

	/**
	 * 게시글 전체 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<Free> - 게시글 전체 조회 결과 리스트
	 */
	public List<Free> getList(AdFreePaging adFreePaging);

	/**
	 * 페이징 객체 생성
	 * 
	 * 요청파라미터 curPage를 구한다
	 * 테이블과 curPage값을 이용하여 Paging객체를 구하여 반환한다
	 * 
	 * @param req - 요청정보 객체
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public AdFreePaging getPaging(HttpServletRequest req);
	
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
	 * 첨부파일 정보 조회
	 * 
	 * @param viewFree - 첨부파일과 연결된 게시글번호를 포함한 DTO객체
	 * @return FreeFile - 첨부파일 정보 DTO객체
	 */
	public FreeFile viewFile(Free viewFree);
	
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
	
	
	
	


}
