package com.hello.helloSpring.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hello.helloSpring.common.intercepter.TokenCheckInterceptor;

/**
 * 스프링의 웹 설정을 담당하는 클래스다 라고 지정해주는 어노테이션
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	// 인터셉터 등록
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		// 토큰, 크로스 브라우징 등등 응용 가능
		registry.addInterceptor(new TokenCheckInterceptor())
		.addPathPatterns("/**")// 모든 경로에 대해 인터셉터 적용
		.excludePathPatterns("/getLoginToken")// 해당 경로에 대해 인터셉터 예외 적용
		.excludePathPatterns("/login/**")
		.excludePathPatterns("/board/*Form*")
		.excludePathPatterns("/board/boardList.html")
		.excludePathPatterns("/js/**")
		;
		
	}// method 
	
}// class
