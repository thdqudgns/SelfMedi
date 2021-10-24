package com.fulldoping.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.product.dto.CompBasket;
import com.fulldoping.product.dto.ProductInfo;
import com.fulldoping.product.service.face.ProductService;
import com.fulldoping.product.service.impl.ProductServiceImpl;

@WebServlet("/basket/view")
public class BasketViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductService productService = new ProductServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter out = resp.getWriter();
		//로그인 되어있지 않으면 리다이렉트 
		if( req.getSession().getAttribute("login") == null
				|| !(boolean)req.getSession().getAttribute("login") ) {
			out.println("로그인이 필요합니다.");
			req.getRequestDispatcher("/WEB-INF/views/notlogin.jsp").forward(req, resp);
//			resp.sendRedirect("/");

			return;
		}

		//userNo = 회원번호를 담고 있는 객체
		int userNo = (int) req.getSession().getAttribute("userNo");
//		System.out.println(userNo);
		
		//회원번호로 비교함에있는 상품리스트 조회
		//productId 담고있는 객체
		List<CompBasket> basketList = productService.getBasketList(userNo);
//		System.out.println(basketList);
//		System.out.println(basketList.get(0).getProductId());
		
		List<ProductInfo> productInfo = productService.getProductList(basketList);
		
		
		req.setAttribute("productInfo", productInfo);
		
//		System.out.println(productInfo);
		req.getRequestDispatcher("/WEB-INF/views/product/basket.jsp").forward(req, resp);
	}
}
