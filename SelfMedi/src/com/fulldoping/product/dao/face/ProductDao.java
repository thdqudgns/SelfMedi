package com.fulldoping.product.dao.face;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.fulldoping.product.dto.CompBasket;
import com.fulldoping.product.dto.NutrientKind;
import com.fulldoping.product.dto.ProductInfo;
import com.fulldoping.product.dto.SymptomCode;
import com.fulldoping.util.PagingProduct;

public interface ProductDao {

	/**
	 * ProductInfo테이블 전체조회
	 * 
	 * @param conn - DB연결 객체
	 * @param paging - 페이징 정보 객체
	 * @return List<ProductInfo> - ProductInfo테이블 전체 조회 결과 리스트
	 */
	public List<ProductInfo> selectAll(Connection conn, PagingProduct paging);

	/**
	 * 총 상품 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - ProductInfo테이블 전체 행 수 조회 결과
	 */
	public int selectCntAll(Connection conn);





	/**
	 * 상품 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param search - 검색어
	 * @return int - ProductInfo테이블 전체 행 수 조회 결과
	 */
	public int selectCntBySearch(Connection conn, String search);
	
	/**
	 * 상품 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param targetId - 검색 대상
	 * @return int - ProductInfo테이블 전체 행 수 조회 결과
	 */
	public int selectCateCntByTarget(Connection conn, int targetId);
	
	/**
	 * 상품 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param symptomId - 검색 증상
	 * @return int - ProductInfo테이블 전체 행 수 조회 결과
	 */
	public int selectCateCntBySymptom(Connection conn, int symptomId);
	
	/**
	 * 상품 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param nutId - 검색 영양소
	 * @return int - ProductInfo테이블 전체 행 수 조회 결과
	 */
	public int selectCateCntByNutrient(Connection conn, int nutId);
	
	/**
	 * 카테고리 대상별 검색
	 * 
	 * @param conn DB연결 객체
	 * @param paging 페이징 정보 객체
	 * @param targetId 대상 코드
	 * @return List<ProductInfo> - ProductInfo테이블 카테고리 검색 조회 결과 리스트
	 */
	public List<ProductInfo> selectCateByTarget(Connection conn, PagingProduct paging, int targetId);

	/**
	 * 카테고리 증상별 검색
	 * 
	 * @param conn DB연결 객체
	 * @param paging 페이징 정보 객체
	 * @param symptomId 증상 코드
	 * @return List<ProductInfo> - ProductInfo테이블 카테고리 검색 조회 결과 리스트
	 */
	public List<ProductInfo> selectCateBySymptom(Connection conn, PagingProduct paging, int symptomId);

	/**
	 * 카테고리 영양소별 검색
	 * 
	 * @param conn DB연결 객체
	 * @param paging 페이징 정보 객체
	 * @param nutId 영양소 코드
	 * @return List<ProductInfo> - ProductInfo테이블 카테고리 검색 조회 결과 리스트
	 */
	public List<ProductInfo> selectCateByNutrient(Connection conn, PagingProduct paging, int nutId);

	/**
	 * 영양제 검색
	 * 
	 * @param conn DB연결 객체
	 * @param paging 페이징 정보 객체
	 * @param search 영양소 코드
	 * @return List<ProductInfo> - ProductInfo테이블 검색 조회
	 */
	public List<ProductInfo> selectBySearch(Connection conn, PagingProduct paging, String search);

	/**
	 * 증상코드 테이블조회
	 * 
	 * @param conn DB연결 객체
	 * @return List<SymptomCode>
	 */
	public List<SymptomCode> selectAllSymptomCode(Connection conn);

	/**
	 * 영양소 테이블 조회
	 * 
	 * @param conn DB연결 객체
	 * @return List<NutrientKind>
	 */
	public List<NutrientKind> selectAllNutrientKind(Connection conn);

	
	/**
	 * 상품아이디로 상품정보 조회
	 * 
	 * @param conn DB연결 객체
	 * @param productId 
	 * @return ProductInfo 하나의 상품 정보
	 */
	public ProductInfo selectProductInfoByProductId(Connection conn, long productId);

	

	

	public List<Map<String, Object>> selectNutirentInfoWithKind(Connection conn, ProductInfo productList);

	
	
	/**
	 * 보관함 삽입
	 * @param conn 
	 * 
	 * @param productId 영양제아이디
	 * @param userNo 사용자넘버
	 * @return 성공 : 1, 실패 0
	 */
	public int insertBasket(Connection conn, long productId, int userNo);
	
	
	//------------------------------------------------------------------
	
	
	/**
	 * CompBasket테이블 전체 조회
	 * 
	 * @param connection - DB연결 객체
	 * @return List<CompBasket> - CompBasket 테이블 조회 결과 리스트
	 */
	public List<CompBasket> selectByuserNo(Connection conn,int userNo);

	/**
	 * CompBasket에 담긴 상품정보 조회
	 * 
	 * @param connection - DB연결 객체
	 * @param basketList - CompBasket 테이블 조회 결과 리스트
	 * @return List<ProductInfo> - ProductInfo 테이블 조회결과 리스트
	 */
	public List<ProductInfo> selectBasketProductInfo(Connection conn, List<CompBasket> basketList);


	/**
	 * 파라미터 전달값 productId를 이용해서 상품정보 조회
	 * 
	 * @param connection 
	 * @param productId - 전달된 파라미터 상품 아이디 
	 * @return - List<ProductInfo>
	 */
	public List<ProductInfo> SelectProductInfo(Connection conn, long[] productId);



	public List<Map<String, Object>> selectNutirentInfoWithKind(Connection conn, long[] productsId);


	/**
	 * 장바구니 삭제
	 * 
	 * @param conn - DB연결객체
	 * @param productId - 상품번호 담은 객체
	 * @return
	 */
	public int deleteBasket(Connection conn, String productId);

	public List<Map<String, Object>> selectNutirentInfoWithKind(Connection conn, String productId);

	
	public List<Map<String, Object>> selectNutirentInfoWithKind(Connection conn, long productId);

	/**
	 * 합산정보 조회
	 * 
	 * @param connection - DB연결 객체
	 * @param productsId - 상품번호 객체
	 * @return - List<Map<String, Object>> 합산정보 객체 반환
	 */
	public List<Map<String, Object>> SelectNutInfo(Connection conn, long[] productsId);





}
