package com.fulldoping.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.product.service.face.ProductService;
import com.fulldoping.product.service.impl.ProductServiceImpl;


@WebServlet("/delete/basket")
public class DeleteBasketController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ProductService productService = new ProductServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String productId =  req.getParameter("deletebtn");
		
//		System.out.println("productId = " + productId);
		
		
		productService.delete(productId);
		
		resp.sendRedirect("/basket/view");
	}
}
