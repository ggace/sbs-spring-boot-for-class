package com.min.sbs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.min.sbs.util.Util;

import lombok.extern.log4j.Log4j2;



@Log4j2
@Component
public class ConnectingInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String server = Util.format("%s:%s", request.getLocalAddr(), request.getLocalPort());
		
		String client = Util.format("%s:%s", request.getRemoteAddr(), request.getRemotePort());
		
		String method = request.getMethod();
		
		String protocol = request.getProtocol();
		
		String query = "";
		
		String queryString = request.getQueryString();
		
		if(queryString == null) {
			query = "";
		}
		else {
			query = "?"+queryString;
		}
		
		
		String url = server+request.getRequestURI() + query;
		
		String output = Util.format("%s [%s] %s : %s", protocol, method, client, url);
		
		System.out.println(output);
		
		log.info(output);
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
