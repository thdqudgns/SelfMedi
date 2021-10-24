package com.fulldoping.admin.product.service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fulldoping.admin.product.dao.face.AdProductDao;
import com.fulldoping.admin.product.dao.impl.AdProductDaoImpl;
import com.fulldoping.admin.product.paging.AdProductPaging;
import com.fulldoping.admin.product.service.face.AdProductService;
import com.fulldoping.common.JDBCTemplate;
import com.fulldoping.product.dto.NutrientInfo;
import com.fulldoping.product.dto.NutrientKind;
import com.fulldoping.product.dto.ProductInfo;
import com.fulldoping.product.dto.SymptomCode;
import com.fulldoping.product.dto.SymptomInfo;
import com.fulldoping.product.dto.TargetCode;
import com.fulldoping.product.dto.TargetInfo;



public class AdProductServiceImpl implements AdProductService{

	//DAO 객체 생성
	private AdProductDao adProductDao = new AdProductDaoImpl();
	
	@Override
	public AdProductPaging getPaging(HttpServletRequest req) {

		//전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}
		
		//Board 테이블의 총 게시글 수를 조회한다
		int totalCount = adProductDao.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging객체 생성
		AdProductPaging paging = new AdProductPaging(totalCount, curPage);
		
		return paging;
	}

	@Override
	public List<ProductInfo> getProduct(AdProductPaging paging) {

		return adProductDao.selectAllProduct(JDBCTemplate.getConnection(), paging);
	}

	@Override
	public List<TargetCode> getTargetCode() {
		return adProductDao.selectAllTargetCode(JDBCTemplate.getConnection());
	}

	@Override
	public List<SymptomCode> getSymptomCode() {
		return adProductDao.selectAllSymptomCode(JDBCTemplate.getConnection());
	}

	@Override
	public List<NutrientKind> getNutrientKind() {
		return adProductDao.selectAllNutrientKind(JDBCTemplate.getConnection());
	}

	@Override
	public ProductInfo getAddProductInfo(HttpServletRequest req) {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		ProductInfo productInfo = new ProductInfo();
		
		productInfo.setProductId( Long.parseLong(req.getParameter("productId")));
		productInfo.setProductName(req.getParameter("productName"));
		productInfo.setManuCom(req.getParameter("manuCom"));
		productInfo.setType(req.getParameter("type"));
		productInfo.setImage(req.getParameter("image"));
		productInfo.setPurchaseLink(req.getParameter("purchaseLink"));
		productInfo.setAllergyInfo(req.getParameter("allergyInfo"));
		productInfo.setStarScore(req.getParameter("starScore"));
		
		
		
		return productInfo;
	}

	@Override
	public TargetInfo getAddTargetInfo(HttpServletRequest req) {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		TargetInfo targetInfo = new TargetInfo();
		
		targetInfo.setProductId( Long.parseLong(req.getParameter("productId")));
		targetInfo.setTargetId( Integer.parseInt(req.getParameter("targetCode")));
		
		return targetInfo;
	}

	@Override
	public List<SymptomInfo> getAddSymptomInfo(HttpServletRequest req) {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String[] symptomCodes = req.getParameterValues("symptomCode");
				
		List<SymptomInfo> symptomInfo1 = new ArrayList<>();
		
	
		
		for (int i = 0; i<symptomCodes.length; i++) {
			SymptomInfo s = new SymptomInfo();
		
			s.setProductId( Long.parseLong(req.getParameter("productId")));
			s.setSymptomId(Integer.parseInt(symptomCodes[i]));
			symptomInfo1.add(s);
		}	
		
		return symptomInfo1;
	}

	@Override
	public List<NutrientInfo> getAddNutrientInfo(HttpServletRequest req) {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String[] nutirentInfoes = req.getParameterValues("nutirentInfo");
		String[] nutrientContents = req.getParameterValues("nutrientContent");
				
		List<NutrientInfo> nutrientInfo = new ArrayList<>();
		
		
		for (int i = 0; i<nutirentInfoes.length; i++) {
			NutrientInfo n = new NutrientInfo();
			
			n.setProductId(Long.parseLong(req.getParameter("productId")));
			n.setNutId(Integer.parseInt(nutirentInfoes[i]));
			n.setNutContent(nutrientContents[i]);
			
			nutrientInfo.add(n);
		}		
		
		return nutrientInfo;
	}

	@Override
	public boolean insertProductInfo(HttpServletRequest req, ProductInfo productInfo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int res = adProductDao.insertProductInfo(JDBCTemplate.getConnection(), productInfo);

		if( res > 0 ) {
			JDBCTemplate.commit(conn);
			return true;
		} else {
			JDBCTemplate.rollback(conn);
			return false;
		}
	}

	@Override
	public boolean insertTargetInfo(HttpServletRequest req, TargetInfo targetInfo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int res = adProductDao.insertTargetInfo(JDBCTemplate.getConnection(), targetInfo);

		if( res > 0 ) {
			JDBCTemplate.commit(conn);
			return true;
		} else {
			JDBCTemplate.rollback(conn);
			return false;
		}
	}

	@Override
	public boolean insertSymptomInfo(HttpServletRequest req, List<SymptomInfo> symptomInfo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int res = adProductDao.insertSymptomInfo(JDBCTemplate.getConnection(), symptomInfo);

		if( res > 0 ) {
			JDBCTemplate.commit(conn);
			return true;
		} else {
			JDBCTemplate.rollback(conn);
			return false;
		}
	}

	@Override
	public boolean insertNutrientInfo(HttpServletRequest req, List<NutrientInfo> nutrientInfo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int res = adProductDao.insertNutrientInfo(JDBCTemplate.getConnection(), nutrientInfo);

		if( res > 0 ) {
			JDBCTemplate.commit(conn);
			return true;
		} else {
			JDBCTemplate.rollback(conn);
			return false;
		}
	}

	@Override
	public boolean deleteProduct(long productId) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		
		System.out.println("productId" + productId);
		int res1 = adProductDao.deleteProductTargetInfo(JDBCTemplate.getConnection(), productId);
		System.out.println("1성공" + res1);
		int res2 = adProductDao.deleteProductSymptomInfo(JDBCTemplate.getConnection(), productId);
		System.out.println("2성공" + res2);
		int res3 = adProductDao.deleteProductNutrientInfo(JDBCTemplate.getConnection(), productId);
		System.out.println("3성공" + res3);
		int res4 = adProductDao.deleteProductCompBasket(JDBCTemplate.getConnection(), productId);
		System.out.println("4성공" + res4);
		
			JDBCTemplate.commit(conn);
			System.out.println("1234성공");
			int res5 = adProductDao.deleteProductInfo(JDBCTemplate.getConnection(), productId);
			if( res5 > 0 ) {
				System.out.println("5성공");
				
				JDBCTemplate.commit(conn);
				return true;
			} else {
				System.out.println("5실패");
				JDBCTemplate.rollback(conn);
				return false;
			}

	}

	@Override
	public ProductInfo getUpdateProductInfo(long productId) {
		
		return adProductDao.selectProductInfo(JDBCTemplate.getConnection(),productId);
	}

	@Override
	public int getUpdateTargetInfo(long productId) {
		return adProductDao.selectTargetInfo(JDBCTemplate.getConnection(),productId);
	}

	@Override
	public List<SymptomInfo> getUpdateSymptomInfo(long productId) {
		return adProductDao.selectSymtomInfo(JDBCTemplate.getConnection(),productId);
	}

	@Override
	public List<NutrientInfo> getUpdateNutrientInfo(long productId) {
		return adProductDao.selectNutrientInfo(JDBCTemplate.getConnection(),productId);
	}

	@Override
	public void update(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		long productId = Long.parseLong(req.getParameter("productId"));
		
		ProductInfo productInfo = new ProductInfo();
		
		productInfo.setProductId( Long.parseLong(req.getParameter("productId")));
		productInfo.setProductName(req.getParameter("productName"));
		productInfo.setManuCom(req.getParameter("manuCom"));
		productInfo.setType(req.getParameter("type"));
		productInfo.setImage(req.getParameter("image"));
		productInfo.setPurchaseLink(req.getParameter("purchaseLink"));
		productInfo.setAllergyInfo(req.getParameter("allergyInfo"));
		productInfo.setStarScore(req.getParameter("starScore"));
		
		System.out.println("productInfo"+productInfo);
		
		if(adProductDao.updateProductInfo(conn,productInfo)>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		
		TargetInfo targetInfo = new TargetInfo();
		
		targetInfo.setProductId( Long.parseLong(req.getParameter("productId")));
		targetInfo.setTargetId( Integer.parseInt(req.getParameter("targetCode")));
		
		System.out.println("targetInfo"+targetInfo);
		
		
		if(adProductDao.deleteProductTargetInfo(conn,productId)>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		if(adProductDao.insertTargetInfo(conn,targetInfo)>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		
		String[] symptomCodes = req.getParameterValues("symptomCode");
		
		List<SymptomInfo> symptomInfo = new ArrayList<>();
		
		
		for (int i = 0; i<symptomCodes.length; i++) {
			SymptomInfo s = new SymptomInfo();
		
			s.setProductId( Long.parseLong(req.getParameter("productId")));
			s.setSymptomId(Integer.parseInt(symptomCodes[i]));
			symptomInfo.add(s);
		}
		System.out.println("symptomInfo1"+symptomInfo);
		
		if(adProductDao.deleteProductSymptomInfo(conn,productId)>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		if(adProductDao.insertSymptomInfo(conn,symptomInfo)>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		
		String[] nutirentInfoes = req.getParameterValues("nutirentInfo");
		String[] nutrientContents = req.getParameterValues("nutrientContent");
				
		List<NutrientInfo> nutrientInfo = new ArrayList<>();
		
		
		for (int i = 0; i<nutirentInfoes.length; i++) {
			NutrientInfo n = new NutrientInfo();
			
			n.setProductId(Long.parseLong(req.getParameter("productId")));
			n.setNutId(Integer.parseInt(nutirentInfoes[i]));
			n.setNutContent(nutrientContents[i]);
			
			nutrientInfo.add(n);
		}

		
		System.out.println("nutrientInfo"+nutrientInfo);
		
		if(adProductDao.deleteProductNutrientInfo(conn,productId)>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		if(adProductDao.insertNutrientInfo(conn,nutrientInfo)>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		
	}



}
