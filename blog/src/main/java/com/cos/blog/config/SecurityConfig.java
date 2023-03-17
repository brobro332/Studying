package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//Bean 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration // IoC 관리
public class SecurityConfig {
	
	@Bean
	 BCryptPasswordEncoder encode() {
	  return new BCryptPasswordEncoder();
	 }

	 @Bean
	 SecurityFilterChain configure(HttpSecurity http) throws Exception {
	  http
	  	.csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두는게 좋음)
	    .authorizeRequests() // 요청이 들어왔을 때
	    	.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**") // url이 /auth/**이면
	    	.permitAll() // 허용한다.
	    	.anyRequest() // 이외의 요청은
	    	.authenticated() // 인증이 돼야 한다.
	    .and() // 그리고
	    	.formLogin() // 인증이 돼야 하면
	    	.loginPage("/auth/loginForm"); // 로그인 페이지로 보내라.
	  
	  return http.build();
	 }
}
