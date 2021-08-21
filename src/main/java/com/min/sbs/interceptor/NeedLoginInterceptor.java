package com.min.sbs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.min.sbs.dto.Rq;

@Component
public class NeedLoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Rq rq = (Rq)request.getAttribute("rq");
		if (!rq.isLogined()) {
			rq.printHistoryBack("로그인 후 사용해주세요");
			return false;
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
