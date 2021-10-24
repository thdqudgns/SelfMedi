package com.fulldoping.admin.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.admin.product.paging.AdProductPaging;
import com.fulldoping.admin.product.service.face.AdProductService;
import com.fulldoping.admin.product.service.impl.AdProductServiceImpl;
import com.fulldoping.product.dto.ProductInfo;

@WebServlet("/ad/product/list")
public class AdProductListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdProductService adProductService = new AdProductServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("[TEST] /ad/product/list [GET]");
		
		AdProductPaging paging = adProductService.getPaging(req);
		
		List<ProductInfo> productList = adProductService.getProduct(paging);
		
		//조회결과 MODEL값 전달
		req.setAttribute("productList", productList);
		
		//페이징 정보 MODEL값 전달
		req.setAttribute("paging", paging);
		
		
		req.getRequestDispatcher("/WEB-INF/views/admin/product/productlist.jsp").forward(req, resp);
	}

}
