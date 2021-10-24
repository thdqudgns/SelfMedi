package com.fulldoping.product.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fulldoping.common.JDBCTemplate;
import com.fulldoping.product.dao.face.ProductDao;
import com.fulldoping.product.dao.impl.ProductDaoImpl;
import com.fulldoping.product.dto.CompBasket;
import com.fulldoping.product.dto.NutrientKind;
import com.fulldoping.product.dto.ProductInfo;
import com.fulldoping.product.dto.SymptomCode;
import com.fulldoping.product.service.face.ProductService;
import com.fulldoping.util.PagingProduct;

public class ProductServiceImpl implements ProductService {

	//BoardDao 객체 생성
	private ProductDao productDao = new ProductDaoImpl();
	

	@Override
	public List<ProductInfo> getAllProduct(PagingProduct paging) {

		//상품 전체 조회 결과 처리 - 페이징 추가
		return productDao.selectAll(JDBCTemplate.getConnection(), paging);
	}

	@Override
	public PagingProduct getPaging(HttpServletRequest req) {



		//전달파라미터 curPage 파싱
		String paramCurPage = req.getParameter("curPage");
		String paramSearch = req.getParameter("search");
		String paramTargetId = req.getParameter("targetId");
		String paramSymptomId = req.getParameter("symptomId");
		String paramNutId = req.getParameter("nutId");

		int curPage = 0;
		String Search = null;
		int TargetId = 0;
		int SymptomId = 0;
		int NutId = 0;
		int totalCount = 0;
		if(paramSearch != null && !"".equals(paramSearch)) {
			Search = paramSearch;
			System.out.println("검색어 입력");
			totalCount = productDao.selectCntBySearch(JDBCTemplate.getConnection(),Search);	
		} else if(paramTargetId != null && !"".equals(paramTargetId)) {
			TargetId = Integer.parseInt(paramTargetId);
			totalCount = productDao.selectCateCntByTarget(JDBCTemplate.getConnection(), TargetId);
		} else if (paramSymptomId != null && !"".equals(paramSymptomId)) {
			SymptomId = Integer.parseInt(paramSymptomId);
			totalCount = productDao.selectCateCntBySymptom(JDBCTemplate.getConnection(), SymptomId);
		} else if(paramNutId != null && !"".equals(paramNutId)) {
			NutId = Integer.parseInt(paramNutId);
			totalCount = productDao.selectCateCntByNutrient(JDBCTemplate.getConnection(), NutId);
		} else {
			totalCount = productDao.selectCntAll(JDBCTemplate.getConnection());
		}


		if(paramCurPage != null && !"".equals(paramCurPage)) {
			curPage = Integer.parseInt(paramCurPage);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}

		//Paging객체 생성
		PagingProduct paging = new PagingProduct(totalCount, curPage);

		return paging;
	}

	@Override
	public List<ProductInfo> getProduct(HttpServletRequest req, PagingProduct paging) {

		String paramSearch = req.getParameter("search");
		String paramTargetId = req.getParameter("targetId");
		String paramSymptomId = req.getParameter("symptomId");
		String paramNutId = req.getParameter("nutId");


		String Search = null;
		int TargetId = 0;
		int SymptomId = 0;
		int NutId = 0;
		if(paramSearch != null && !"".equals(paramSearch)) {
			Search = paramSearch;
			System.out.println("검색어 입력");
			return productDao.selectBySearch(JDBCTemplate.getConnection(), paging, Search);
		} else if(paramTargetId != null && !"".equals(paramTargetId)) {
			TargetId = Integer.parseInt(paramTargetId);
			return productDao.selectCateByTarget(JDBCTemplate.getConnection(), paging, TargetId);
		} else if (paramSymptomId != null && !"".equals(paramSymptomId)) {
			SymptomId = Integer.parseInt(paramSymptomId);
			return productDao.selectCateBySymptom(JDBCTemplate.getConnection(), paging, SymptomId);
		} else if(paramNutId != null && !"".equals(paramNutId)) {
			NutId = Integer.parseInt(paramNutId);
			return productDao.selectCateByNutrient(JDBCTemplate.getConnection(), paging, NutId);
		} else {
			System.out.println("카테고리 선택 안함");
		}

		return productDao.selectAll(JDBCTemplate.getConnection(), paging);
	}

	@Override
	public List<SymptomCode> getSymptomCode() {

		return productDao.selectAllSymptomCode(JDBCTemplate.getConnection());
	}

	@Override
	public List<NutrientKind> getNutrientKind() {
		return productDao.selectAllNutrientKind(JDBCTemplate.getConnection());
	}

	@Override
	public ProductInfo getProductInfoByProductId(HttpServletRequest req) {

		String paramProductId = req.getParameter("productId");
		long productId = Long.parseLong(paramProductId);
		return productDao.selectProductInfoByProductId(JDBCTemplate.getConnection(), productId);
	}





	@Override
	public List<Map<String, Object>> getNutirentInfoWithKind(long[] productsId) {
	   return productDao.selectNutirentInfoWithKind(JDBCTemplate.getConnection(),productsId);
	}

	@Override
	public boolean addProductInBasket(HttpServletRequest req) {

		Connection conn = JDBCTemplate.getConnection();
		String paramProductId = req.getParameter("productId");
		long productId = Long.parseLong(paramProductId);

		int userNo = (int) req.getSession().getAttribute("userNo");

		int res = productDao.insertBasket(JDBCTemplate.getConnection(), productId, userNo);


		if( res > 0 ) {
			JDBCTemplate.commit(conn);
			return true;
		} else {
			JDBCTemplate.rollback(conn);
			return false;
		}


	}
	
	//--------------------위 상품진열관련/ 아래 보관함 관련------------------
	
	
	@Override
	public List<CompBasket> getBasketList(int userNo) {
		
		return productDao.selectByuserNo(JDBCTemplate.getConnection(),userNo);
	}


	@Override
	public List<ProductInfo> getProductList(List<CompBasket> basketList) {
		
		return productDao.selectBasketProductInfo(JDBCTemplate.getConnection(),basketList);
	}

	@Override
	public List<ProductInfo> getProductInfo(long[] productId) {
		
		List<ProductInfo> ProductInfo = productDao.SelectProductInfo(JDBCTemplate.getConnection(),productId);
		
		
		
		return ProductInfo;
	}



	@Override
	public void delete(String productId) {
		Connection conn = JDBCTemplate.getConnection();
		
		if( productDao.deleteBasket(conn, productId) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}

	   @Override
	   public List<Map<String, Object>> getNutirentInfoWithKind(ProductInfo productList) {
	      return productDao.selectNutirentInfoWithKind(JDBCTemplate.getConnection(), productList);
	   }

	@Override
	public List<Map<String, Object>> getNutirentInfoWithKind(String productId) {
		return productDao.selectNutirentInfoWithKind(JDBCTemplate.getConnection(), productId);
	}

	@Override
	public List<Map<String, Object>> getNutirentInfoWithKind(long productId) {
		return productDao.selectNutirentInfoWithKind(JDBCTemplate.getConnection(), productId);
	}

	@Override
	public List<Map<String, Object>> getNutinfo(long[] productsId) {
		return productDao.SelectNutInfo(JDBCTemplate.getConnection(), productsId);
	}











	


}
