package com.fulldoping.product.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.product.dto.ProductInfo;
import com.fulldoping.product.service.face.ProductService;
import com.fulldoping.product.service.impl.ProductServiceImpl;



@WebServlet("/product/detail")
public class ProductDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductService productService = new ProductServiceImpl();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("/product/detail [GET]");
		
		
		
		ProductInfo productList = productService.getProductInfoByProductId(req);
		
		System.out.println("productInfo : " + productList);
		

		
		List<Map<String, Object>> nikList = productService.getNutirentInfoWithKind(productList);
//		for( Map<String, Object> m : nikList ) {
//			System.out.println(m);
//		}
		
		req.setAttribute("productInfo", productList);
		req.setAttribute("nikList", nikList);
		
		String paramProductId = req.getParameter("productId");
		if(paramProductId != null && !"".equals(paramProductId)) {
			req.getRequestDispatcher("/WEB-INF/views/product/detail.jsp").forward(req, resp);
		}else{
			resp.sendRedirect("/product/display");
		}
	}
}
