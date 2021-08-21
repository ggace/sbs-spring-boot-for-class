package com.min.sbs.dto;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.min.sbs.util.Util;

import lombok.Getter;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rq {
	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	
	public Rq(HttpServletRequest request, HttpServletResponse response) {
		
		boolean isLogined = false;
		int loginedMemberId = 0;
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		
		this.isLogined = isLogined;
		this.loginedMemberId = loginedMemberId;
		
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		
		this.request.setAttribute("rq", this);
	}

	public void printHistoryBack(String msg) {
		
		response.setContentType("text/html; charset=utf-8");
		
		println(Util.jsHistoryBack(msg));
	}

	private void println(String msg) {
		print(msg + "\n");
	}

	private void print(String msg) {
		try {
			response.getWriter().append(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void login(Member member) {
		session.setAttribute("loginedMemberId", member.getId());
	}

	public void logout() {
		session.removeAttribute("loginedMemberId");
	}

	public String historyBackJsOnView(String msg) {
		request.setAttribute("msg", msg);
		request.setAttribute("historyBack", true);
		return "/usr/common/js";
	}
	
	// 이 메서드는 Rq객체가 자연스럽게 생성되도록 유도하는 역할을 한다.
	// 지우면 안되고 
	//편의를 위해 BeforeActionInterceptor 에서 꼭 호출해주어여한다.
	public void init() {
		
	}
	
}
