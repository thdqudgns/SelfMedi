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

@WebServlet("/comparison")
public class ComparisonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService productService = new ProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		
		//전달 파라미터 얻기
		String[] productid = req.getParameterValues("productId");
		String[] productId = req.getParameterValues("productId");
		
		long[] productsId = Arrays.stream(productid).mapToLong(Long::parseLong).toArray();

//		for (int i = 0; i < productsId.length; i++) {
//			
//			System.out.println(productsId[i]);
//		}
		
		
		List<Map<String, Object>> nikList1 = productService.getNutirentInfoWithKind(productId[0]);
		List<Map<String, Object>> nikList2 = productService.getNutirentInfoWithKind(productId[1]);
//		for( Map m : nikList ) {
//			System.out.println(m);
//		}
		
		//상품정보 
		List<ProductInfo> productList = productService.getProductInfo(productsId);
//		System.out.println("productList : " + productList);
		
		req.setAttribute("productInfo", productList);
		req.setAttribute("nikList1", nikList1);
		req.setAttribute("nikList2", nikList2);
		
		System.out.println("prolist" + productList);
		System.out.println("nik1" + nikList1);
		System.out.println("nik2" + nikList2);
		
		req.getRequestDispatcher("/WEB-INF/views/product/compview.jsp").forward(req, resp);
	}

}
