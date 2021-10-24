package com.fulldoping.product.service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fulldoping.product.dto.CompBasket;
import com.fulldoping.product.dto.NutrientKind;
import com.fulldoping.product.dto.ProductInfo;
import com.fulldoping.product.dto.SymptomCode;
import com.fulldoping.util.PagingProduct;

public interface ProductService {

	/**
	 * ProductInfo테이블 전체조회
	 * 페이징 처리
	 * 
	 * @param paging - paging 정보 객체
	 * @return List<ProductInfo> - 테이블 전체 조회 리스트
	 */
	public List<ProductInfo> getAllProduct(PagingProduct paging);
	/**
	 * 페이징 객체 생성
	 * 
	 * 요청파라미터 curPage를 구한다
	 * ProductInfo테이블과 curPage값을 이용하여 Paging객체를 구하여 반환한다
	 * 
	 * @param req - 요청정보 객체
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public PagingProduct getPaging(HttpServletRequest req);
	


	

	

	
	/**
	 * ProductInfo 카테고리별 조회
	 * 
	 * @param req 요청정보 객체 (선택한 카테고리)
	 * @param paging 페이징정보
	 * @return 선택한 카테고리 상품리스트
	 */
	public List<ProductInfo> getProduct(HttpServletRequest req, PagingProduct paging);
	
	/**
	 * SymptomCode 테이블 정보 조회
	 * 
	 * @return List<SymptomCode> - SymptomCode 테이블 정보
	 */
	public List<SymptomCode> getSymptomCode();
	
	/**
	 * NutrientKind 테이블 정보 조회
	 * 
	 * @return List<NutrientKind> - NutrientKind 테이블 정보
	 */
	public List<NutrientKind> getNutrientKind();
	
	/**
	 * ProductInfo 테이블 정보 조회
	 * 
	 * @param req
	 * @return ProductInfo 하나의 튜플 조회
	 */
	public ProductInfo getProductInfoByProductId(HttpServletRequest req);
	
	/**
	 * 
	 * 
	 * @param productsId 
	 * @return
	 */
	public List<Map<String, Object>> getNutirentInfoWithKind(long[] productsId);
	
	
	/**
	 * 상품추가
	 * 
	 * @param req 세션정보, productId
	 * @return 성공:1 , 실패0
	 */
	public boolean addProductInBasket(HttpServletRequest req);

	
	
	
	
	//----------------------------------------------------------
	/**
	 * CompBasket테이블 전체 조회
	 * 
	 * 
	 * @param userNo - 회원번호
	 * @return List<CompBasket> - 비교함 전체 조회 리스트
	 */
	public List<CompBasket> getBasketList(int userNo);
	
	/**
	 * 보관함에 저장된 상품 정보 조회
	 * 
	 * @param basketList - 보관함에 저장된 상품(상품아이디, 회원번호) 객체
	 * @return - List<ProductInfo> 보관함에 저장된 상품 정보 조회 리스트
	 */
	public List<ProductInfo> getProductList(List<CompBasket> basketList);
	
	/**
	 * 전달된 파라미터 productId를 이용하여 상품정보 및 영양소 정보 조회
	 * 
	 * 
	 * @param productsId - 파라미터 전달 값
	 * @return - List<ProductInfo> 보관함에서 전달된 상품정보 조회 리스트
	 */
	public List<ProductInfo> getProductInfo(long[] productsId);
	
	
	public List<Map<String, Object>> getNutirentInfoWithKind(ProductInfo productList);
	
	/**
	 * productId로 조회된 테이블컬럼 삭제
	 * 
	 * @param productId - productId 담은 객체
	 */
	public void delete(String productId);
	
	
	public List<Map<String, Object>> getNutirentInfoWithKind(String productId);
	
	public List<Map<String, Object>> getNutirentInfoWithKind(long productId);
	
	/**
	 * 전달받은 상품번호로 DB를 조회하여 합산정보를 얻는다
	 * 
	 * @param productsId - 상품번호 객체
	 * @return - List<Map<String, Object>> 조회정보 객체
	 */
	public List<Map<String, Object>> getNutinfo(long[] productsId);
	

	
	

	

}
