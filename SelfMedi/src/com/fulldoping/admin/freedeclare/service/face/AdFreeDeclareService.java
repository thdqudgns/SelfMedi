package com.fulldoping.admin.freedeclare.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fulldoping.admin.freedeclare.paging.FreeDeclarePaging;
import com.fulldoping.free.dto.FreeDeclare;
import com.fulldoping.free.dto.FreeFile;

public interface AdFreeDeclareService {

	/**
	 * 페이징 처리 추가 된 게시글 전체 조회
	 * 
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<FreeDeclare> - 게시글 전체 조회 결과 리스트
	 */
	public List<FreeDeclare> getList(FreeDeclarePaging freeDeclarePaging);
	
	/**
	 * 페이징 객체 생성
	 * 
	 * 요청파라미터 curPage를 구한다
	 * Free테이블과 curPage값을 이용하여 Paging객체를 구하여 반환한다
	 * 
	 * @param req - 요청정보 객체
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public FreeDeclarePaging getPaging(HttpServletRequest req);

	/**
	 * 요청 파라미터 얻기
	 * 
	 * @param req - 요청정보 객체
	 * @return FreeDeclare - 전달파라미터 boardNo를 포함한 객체
	 */
	public FreeDeclare getboardNo(HttpServletRequest req);

	/**
	 * 주어진 boardNo을 이용하여 게시글을 조회한다
	 * 
	 * @param boardNo - boardNo를 가지고 있는 객체
	 * @return FreeDeclare - 조회된 게시글
	 */
	public FreeDeclare view(FreeDeclare boardNo);

	/**
	 * Notice 객체의 id를 이용한 닉네임 조회
	 * 
	 * @param viewFree - 조회할 게시글 정보
	 * @return String - 게시글 작성자의 닉네임
	 */
//	public String getuserNick(FreeDeclare viewFreeDeclare);

	/**
	 * 첨부파일 정보 조회
	 * 
	 * @param viewFile - 첨부파일과 연결된 게시글번호를 포함한 DTO객체
	 * @return FreeFile - 첨부파일 정보 DTO객체
	 */
	public FreeFile viewFile(FreeDeclare viewFreeDeclare);

	/**
	 * 게시글 삭제
	 * 
	 * @param FreeDeclare - 삭제할 게시글 번호를 가진 객체
	 */
	public void delete(FreeDeclare freeDeclare);

	


}
