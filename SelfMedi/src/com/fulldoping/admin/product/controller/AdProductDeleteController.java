package com.fulldoping.admin.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.admin.product.service.face.AdProductService;
import com.fulldoping.admin.product.service.impl.AdProductServiceImpl;

@WebServlet("/ad/product/delete")
public class AdProductDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdProductService adProductService = new AdProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/ad/product/delete [GET]");
		
		
		String param = req.getParameter("productId");
		
		long productId = Long.parseLong(param);
		
		boolean deleteProductSuccess = adProductService.deleteProduct(productId);
		
		System.out.println("삭제 성공 : " + deleteProductSuccess);
		
		resp.sendRedirect("/ad/product/list");
	}

}
