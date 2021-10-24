package com.fulldoping.admin.product.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fulldoping.admin.product.paging.AdProductPaging;
import com.fulldoping.product.dto.NutrientInfo;
import com.fulldoping.product.dto.NutrientKind;
import com.fulldoping.product.dto.ProductInfo;
import com.fulldoping.product.dto.SymptomCode;
import com.fulldoping.product.dto.SymptomInfo;
import com.fulldoping.product.dto.TargetCode;
import com.fulldoping.product.dto.TargetInfo;

public interface AdProductService {

	
	/**
	 * 페이징 객체 생성
	 * 
	 * @param req
	 * @return
	 */
	public AdProductPaging getPaging(HttpServletRequest req);

	/**
	 * 상품조회
	 * 
	 * @param paging
	 * @return
	 */
	public List<ProductInfo> getProduct(AdProductPaging paging);

	
	/**
	 * TargetCode 테이블조회
	 * 
	 * @return
	 */
	public List<TargetCode> getTargetCode();

	/**
	 * SymptomCode 테이블 조회
	 * 
	 * @return
	 */
	public List<SymptomCode> getSymptomCode();

	/**
	 * NutrientKind 테이블 조회
	 * 
	 * @return
	 */
	public List<NutrientKind> getNutrientKind();

	
	/**
	 * 상품 정보 추출하기
	 * 
	 * 
	 * @param req
	 * @return
	 */
	public ProductInfo getAddProductInfo(HttpServletRequest req);

	
	/**
	 * 대상 정보 추출하기
	 * 
	 * @param req
	 * @return
	 */
	public TargetInfo getAddTargetInfo(HttpServletRequest req);


	/**
	 * 증상 정보 추출하기
	 * 
	 * @param req
	 * @return
	 */
	public List<SymptomInfo> getAddSymptomInfo(HttpServletRequest req);

	
	/**
	 * 영양소정보 추출하기
	 * 
	 * @param req
	 * @return
	 */
	public List<NutrientInfo> getAddNutrientInfo(HttpServletRequest req);

	/**
	 * ProductInfo 테이블 상품 추가 
	 * 
	 * @param req
	 * @param productInfo
	 * @return
	 */
	public boolean insertProductInfo(HttpServletRequest req, ProductInfo productInfo);

	/**
	 * TargetInfo 테이블 상품 추가 
	 * 
	 * @param req
	 * @param targetInfo
	 * @return
	 */
	public boolean insertTargetInfo(HttpServletRequest req, TargetInfo targetInfo);

	/**
	 * SymptomInfo 테이블 상품 추가 
	 * 
	 * @param req
	 * @param symptomInfo
	 * @return
	 */
	public boolean insertSymptomInfo(HttpServletRequest req, List<SymptomInfo> symptomInfo);

	/**
	 * NutrientInfo 테이블 상품 추가 
	 * 
	 * @param req
	 * @param nutrientInfo
	 * @return
	 */
	public boolean insertNutrientInfo(HttpServletRequest req, List<NutrientInfo> nutrientInfo);

	
	
	/**
	 * 상품 제거
	 * 
	 * @param productId
	 * @return
	 */
	public boolean deleteProduct(long productId);
	
	
	

	/**
	 * 수정페이지 입력할 상품정보 조회
	 * 
	 * @param productId - 파라미터로 전달받은 상품 번호
	 * @return ProductInfo 상품정보 
	 */
	public ProductInfo getUpdateProductInfo(long productId);

	/**
	 * 수정페이지 입력할 대상별정보 조회
	 * 
	 * @param productId - 파라미터로 전달받은 상품 번호
	 * @return TargetInfo 대상별 코드
	 */
	public int getUpdateTargetInfo(long productId);
	
	/**
	 * 수정페이지 입력할 증상별정보 조회
	 * 
	 * @param productId - 파라미터로 전달받은 상품 번호
	 * @return List<SymptomInfo> 증상별 코드 리스트
	 */
	public List<SymptomInfo> getUpdateSymptomInfo(long productId);

	/**
	 * 수정페이지 입력할 영양소정보 조회
	 * 
	 * @param productId - 파라미터로 전달받은 상품 번호
	 * @return List<NutrientInfo> 영양소 코드및 정보 리스트
	 */
	public List<NutrientInfo> getUpdateNutrientInfo(long productId);

	/**
	 * 전달받은 req파라미터 값으로 update 실행
	 * 
	 * @param req - 입력받은 값
	 */
	public void update(HttpServletRequest req);



}
