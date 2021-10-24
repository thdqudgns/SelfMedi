package com.fulldoping.admin.product.dao.face;

import java.sql.Connection;
import java.util.List;


import com.fulldoping.admin.product.paging.AdProductPaging;
import com.fulldoping.product.dto.NutrientInfo;
import com.fulldoping.product.dto.NutrientKind;
import com.fulldoping.product.dto.ProductInfo;
import com.fulldoping.product.dto.SymptomCode;
import com.fulldoping.product.dto.SymptomInfo;
import com.fulldoping.product.dto.TargetCode;
import com.fulldoping.product.dto.TargetInfo;

public interface AdProductDao {

	/**
	 * 총 상품갯수 조회
	 * 
	 * @param conn
	 * @return
	 */
	public int selectCntAll(Connection conn);

	
	
	/**
	 * productinfo테이블 전체 조회
	 * 	페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @param conn - DB연결 객체
	 * @return List<ProductInfo> - productinfo테이블 전체 조회 결과 리스트
	 */
	public List<ProductInfo> selectAllProduct(Connection conn, AdProductPaging paging);



	/**
	 * 대상별 테이블 조회
	 * 
	 * @param connection
	 * @return List<TargetCode>
	 */
	public List<TargetCode> selectAllTargetCode(Connection conn);


	/**
	 * 증상별 테이블 조회
	 * 
	 * @param connection
	 * @return
	 */
	public List<SymptomCode> selectAllSymptomCode(Connection conn);


	/**
	 * 영양소 정보 테이블 조회
	 * 
	 * @param conn
	 * @return
	 */
	public List<NutrientKind> selectAllNutrientKind(Connection conn);



	/**
	 * ProductInfo insert
	 * 
	 * @param connection
	 * @param productInfo
	 * @return
	 */
	public int insertProductInfo(Connection connection, ProductInfo productInfo);


	/**
	 * TargetInfo insert
	 * 
	 * @param connection
	 * @param targetInfo
	 * @return
	 */
	public int insertTargetInfo(Connection connection, TargetInfo targetInfo);


	/**
	 * SymptomInfo insert
	 * 
	 * @param connection
	 * @param symptomInfo
	 * @return
	 */
	public int insertSymptomInfo(Connection connection, List<SymptomInfo> symptomInfo);


	/**
	 * NutrientInfo insert
	 * 
	 * @param connection
	 * @param nutrientInfo
	 * @return
	 */
	public int insertNutrientInfo(Connection connection, List<NutrientInfo> nutrientInfo);
	
	/**
	 * productId로 상품정보테이블 조회
	 * 
	 * @param connection - DB연결 객체
	 * @param productId
	 * @return List<ProductInfo> 
	 */
	public ProductInfo selectProductInfo(Connection conn, long productId);


	/**
	 * productId로 대상별정보테이블 조회
	 * 
	 * @param connection- DB연결 객체
	 * @param productId
	 * @return TargetInfo 대상별 정보
	 */
	public int selectTargetInfo(Connection conn, long productId);


	/**
	 * productId로 증상별정보테이블 조회
	 * 
	 * @param connection- DB연결 객체
	 * @param productId
	 * @return List<SymptomInfo> 증상별 정보
	 */
	public List<SymptomInfo> selectSymtomInfo(Connection conn, long productId);

	/**
	 * productId로 영양소정보테이블 조회
	 * 
	 * @param connection - DB연결 객체
	 * @param productId
	 * @return List<NutrientInfo> 영양소 정보
	 */
	public List<NutrientInfo> selectNutrientInfo(Connection conn, long productId);

	/**
	 *  ProductInfo 수정
	 * 
	 * @param conn - DB연결 객체
	 * @param productInfo - 입력값
	 * @return
	 */
	public int updateProductInfo(Connection conn, ProductInfo productInfo);

	/**
	 * TargetInfo 수정
	 * 
	 * @param conn - DB연결 객체
	 * @param targetInfo - 입력값
	 * @return
	 */
	public int updateTargetInfo(Connection conn, TargetInfo targetInfo);

	/**
	 * SymptomInfo 수정
	 * 
	 * @param conn - DB연결 객체
	 * @param symptomInfo1 - 입력값
	 * @return
	 */
	public int updateSymptomInfo(Connection conn, List<SymptomInfo> symptomInfo1);

	/**
	 * deleteProduct targetinfo delete
	 * 
	 * @param connection
	 * @param productId
	 * @return
	 */
	int deleteProductTargetInfo(Connection conn, long productId);





	/**
	 * deleteProduct symptomInfo delete
	 * 
	 * @param connection
	 * @param productId
	 * @return
	 */
	int deleteProductSymptomInfo(Connection conn, long productId);


	/**
	 * deleteProduct nutrientInfo delete
	 * 
	 * @param connection
	 * @param productId
	 * @return
	 */
	int deleteProductNutrientInfo(Connection conn, long productId);

	/**
	 * deleteProduct CompBasket delete
	 * 
	 * @param connection
	 * @param productId
	 * @return
	 */
	int deleteProductCompBasket(Connection conn, long productId);
	
	/**
	 * deleteProduct productInfo delete
	 * 
	 * @param connection
	 * @param productId
	 * @return
	 */
	int deleteProductInfo(Connection conn, long productId);


}
