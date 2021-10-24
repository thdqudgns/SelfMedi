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


@WebServlet("/ad/product/update")
public class AdProductUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdProductService adProductService = new AdProductServiceImpl();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		long productId = Long.parseLong(req.getParameter("productId"));
		System.out.println("productid"+productId);
		
		//productId로 ProductInfo조회
		ProductInfo productInfo = adProductService.getUpdateProductInfo(productId);
		System.out.println(productInfo);
		
		//productId로 TargetInfo조회
		int targetInfo = adProductService.getUpdateTargetInfo(productId);
		System.out.println(targetInfo);
		
		//productId로 SymptomInfo조회
		List<SymptomInfo> symptomInfo = adProductService.getUpdateSymptomInfo(productId);
		System.out.println(symptomInfo);
		
		//productId로 NutrientInfo조회
		List<NutrientInfo> nutrientInfo = adProductService.getUpdateNutrientInfo(productId);
		System.out.println(nutrientInfo);

		List<TargetCode> targetCode = adProductService.getTargetCode();
		
		List<SymptomCode> symptomCode = adProductService.getSymptomCode();
		
		List<NutrientKind> nutrientKind = adProductService.getNutrientKind();
		
		req.setAttribute("targetCode", targetCode);
		req.setAttribute("symptomCode", symptomCode);
		req.setAttribute("nutrientKind", nutrientKind);
		
		req.setAttribute("productInfo", productInfo);
		req.setAttribute("targetInfo", targetInfo);
		req.setAttribute("symptomInfo", symptomInfo);
		req.setAttribute("nutrientInfo", nutrientInfo);
		
		req.getRequestDispatcher("/WEB-INF/views/admin/product/productupdate.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		adProductService.update(req);
		
		resp.sendRedirect("/ad/product/list");
		
	}
}
