package com.fulldoping.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//테스트 코드 작성 - 추후 주석처리
		System.out.println("EncodingFilter - doFilter()");
		
		//요청정보에 대한 한글 인코딩 설정 -> 이제 서블릿쪽에 안 적어도 된다.
		request.setCharacterEncoding("UTF-8");
		
		//아래 코드(chain)를 적지 않으면 화면이 나오지 않는다.
		//요청정보를 컨트롤러로 전달한다
		chain.doFilter(request, response);
		
	}

}
