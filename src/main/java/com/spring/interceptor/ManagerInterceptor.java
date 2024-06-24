package com.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.spring.model.ManagerVO;

public class ManagerInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
			throws Exception{
		HttpSession session = request.getSession();
		ManagerVO vo = (ManagerVO)session.getAttribute("managersession");
		if(vo.getManagerCode().equals("123")) {
			return true;
		}
		else {
			response.sendRedirect("/error");
			System.out.println(vo.getManagerCode());
			return false;
		}
		
	}

}
