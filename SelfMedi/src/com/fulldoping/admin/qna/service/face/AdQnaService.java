package com.fulldoping.admin.qna.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fulldoping.QnA.dto.QnA;
import com.fulldoping.QnA.dto.QnAFile;
import com.fulldoping.admin.qna.paging.AdQnaPaging;

public interface AdQnaService {

	/**
	 * 페이징 처리 추가 된 게시글 전체 조회
	 * 
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<QnA> - 게시글 전체 조회 결과 리스트
	 */
	public List<QnA> getList(AdQnaPaging adQnaPaging);
	
	/**
	 * 페이징 객체 생성
	 * 
	 * 요청파라미터 curPage를 구한다
	 * Free테이블과 curPage값을 이용하여 Paging객체를 구하여 반환한다
	 * 
	 * @param req - 요청정보 객체
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public AdQnaPaging getPaging(HttpServletRequest req);

	/**
	 * 요청 파라미터 얻기
	 * 
	 * @param req - 요청정보 객체
	 * @return FreeDeclare - 전달파라미터 boardNo를 포함한 객체
	 */
	public QnA getboardNo(HttpServletRequest req);

	/**
	 * 주어진 boardNo을 이용하여 게시글을 조회한다
	 * 
	 * @param boardNo - boardNo를 가지고 있는 객체
	 * @return QnADeclare - 조회된 게시글
	 */
	public QnA view(QnA boardNo);

//	public Object getuserNick(QnA viewQna);

	/**
	 * 첨부파일 정보 조회
	 * 
	 * @param viewFile - 첨부파일과 연결된 게시글번호를 포함한 DTO객체
	 * @return QnAFile - 첨부파일 정보 DTO객체
	 */
	public QnAFile viewFile(QnA viewQna);

	/**
	 * 게시글 삭제
	 * 
	 * @param QnA - 삭제할 게시글 번호를 가진 객체
	 */
	public void delete(QnA qna);



	
	


}
