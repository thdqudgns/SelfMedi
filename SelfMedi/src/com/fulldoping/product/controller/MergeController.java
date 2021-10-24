package com.fulldoping.product.controller;

import java.io.IOException;
import java.util.Arrays;
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

@WebServlet("/merge")
public class MergeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductService productService = new ProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//전달 파라미터 얻기
		String[] productId = req.getParameterValues("productId");
		
		long[] productsId = Arrays.stream(productId).mapToLong(Long::parseLong).toArray();

//		System.out.println("productsId = " + productId[0]);
//		System.out.println("productsId = " + productId[1]);
		
		List<Map<String, Object>> nutList = productService.getNutinfo(productsId);
//		List<Map<String, Object>> nikList1 = productService.getNutirentInfoWithKind(productsId[0]);
//		List<Map<String, Object>> nikList2 = productService.getNutirentInfoWithKind(productsId[1]);
//		System.out.println("nikList1 = " + nikList1 );
//		System.out.println("nikList2 = " + nikList2);

		List<ProductInfo> productList = productService.getProductInfo(productsId);
//		System.out.println("productList : " + productList);
		
		req.setAttribute("productInfo", productList);
		req.setAttribute("nutList", nutList);
		
//		  req.setAttribute("nikList1", nikList1);
//		  req.setAttribute("nikList2", nikList2);
		
		
		
//		System.out.println("nikList1 : " + nikList1);
//		System.out.println("nikList2 : " + nikList2);
		
		
		req.getRequestDispatcher("/WEB-INF/views/product/mergeview.jsp").forward(req, resp);
		
	}
}
