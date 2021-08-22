package com.min.sbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.min.sbs.interceptor.BeforeInterceptor;
import com.min.sbs.interceptor.ConnectingInterceptor;
import com.min.sbs.interceptor.NeedLoginInterceptor;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer{
	@Autowired
	BeforeInterceptor beforeInterceptor;
	@Autowired
	ConnectingInterceptor connectingInterceptor;
	
	@Autowired
	NeedLoginInterceptor needLoginInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(connectingInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/resources/**")
		.excludePathPatterns("/error");
		
		registry.addInterceptor(beforeInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/resources/**")
		.excludePathPatterns("/error");
		
		registry.addInterceptor(needLoginInterceptor)
		.addPathPatterns("/usr/article/write")
		.addPathPatterns("/usr/article/doDelete")
		.addPathPatterns("/usr/article/doAdd")
		.addPathPatterns("/usr/article/modify")
		.addPathPatterns("/usr/article/doModify")
		.addPathPatterns("/usr/article/doArticleLike");
		
		
		
	}
}
