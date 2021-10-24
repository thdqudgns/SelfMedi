package com.fulldoping.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.product.dto.ProductInfo;
import com.fulldoping.product.service.face.ProductService;
import com.fulldoping.product.service.impl.ProductServiceImpl;


@WebServlet("/add/basket")
public class AddBasketController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductService productService = new ProductServiceImpl();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/add/basket [GET]");
		
		
	      //로그인 되어있지 않으면 리다이렉트 
	      if( req.getSession().getAttribute("login") == null
	            || !(boolean)req.getSession().getAttribute("login") ) {
	         resp.sendRedirect("/");

	         return;
	      }
	      
//	      boolean addProduct = productService.addProductInBasket(req);
	      
//	      System.out.println("삽입성공or실패 :" + addProduct);

//	      req.setAttribute("addProduct", addProduct);
	      
	      resp.sendRedirect("/basket/view");
	      
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("application/json; charset=utf-8");

		
		System.out.println("/add/basket [POST]");
		System.out.println(req.getParameter("productId"));
		
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId( Long.parseLong(req.getParameter("productId")) );
		
		System.out.println("productInfo.getProductId()"+productInfo.getProductId());
		
		//productInfo 를 이용해서 상품을 보관함에 추가
		boolean addProduct = productService.addProductInBasket(req);
		
		
		resp.getWriter().println("{\"result\": " + addProduct + "}");
		
	}

}
