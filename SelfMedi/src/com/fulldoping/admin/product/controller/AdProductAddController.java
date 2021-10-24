package com.fulldoping.admin.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.admin.product.service.face.AdProductService;
import com.fulldoping.admin.product.service.impl.AdProductServiceImpl;
import com.fulldoping.product.dto.NutrientInfo;
import com.fulldoping.product.dto.NutrientKind;
import com.fulldoping.product.dto.ProductInfo;
import com.fulldoping.product.dto.SymptomCode;
import com.fulldoping.product.dto.SymptomInfo;
import com.fulldoping.product.dto.TargetCode;
import com.fulldoping.product.dto.TargetInfo;


@WebServlet("/ad/product/add")
public class AdProductAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdProductService adProductService = new AdProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/ad/product/add [GET]");
		
		List<TargetCode> targetCode = adProductService.getTargetCode();
		
		List<SymptomCode> symptomCode = adProductService.getSymptomCode();
		
		List<NutrientKind> nutrientKind = adProductService.getNutrientKind();
		
		
		req.setAttribute("targetCode", targetCode);
		req.setAttribute("symptomCode", symptomCode);
		req.setAttribute("nutrientKind", nutrientKind);
		
		
		
		req.getRequestDispatcher("/WEB-INF/views/admin/product/productadd.jsp").forward(req, resp);
	
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		System.out.println("/ad/product/add [POST]");
	
				
//		System.out.println( req.getParameterValues("symptomCode") );
		
//		String[] symptomCodes = req.getParameterValues("symptomCode");
//		
//
//			System.out.println("확인 " + symptomCodes[0]);

		
		ProductInfo productInfo = adProductService.getAddProductInfo(req);
		System.out.println(productInfo);
		
		TargetInfo targetInfo = adProductService.getAddTargetInfo(req);
		System.out.println(targetInfo);
		
		List<SymptomInfo> symptomInfo = adProductService.getAddSymptomInfo(req);
		System.out.println(symptomInfo);
		
		List<NutrientInfo> nutrientInfo = adProductService.getAddNutrientInfo(req);
		System.out.println(nutrientInfo);
		
		boolean addProductInfo = adProductService.insertProductInfo(req, productInfo);
		boolean addTargetInfo = adProductService.insertTargetInfo(req, targetInfo);
		boolean addSymptomInfo = adProductService.insertSymptomInfo(req, symptomInfo);
		boolean addNutrientInfo = adProductService.insertNutrientInfo(req, nutrientInfo);
		
		
		resp.sendRedirect("/ad/product/list");
		
		
		
	}
}
