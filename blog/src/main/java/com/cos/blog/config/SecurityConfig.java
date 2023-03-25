package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cos.blog.config.auth.PrincipalDetailService;

//Bean 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration // IoC 관리
public class SecurityConfig {
	
	@Autowired
	private PrincipalDetailService principalDetailService; 
	
	 @Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	     return authenticationConfiguration.getAuthenticationManager();
	 }
	
	@Bean
	 BCryptPasswordEncoder encodePWD() {
	  return new BCryptPasswordEncoder();
	 }

	// 시큐리티가 대신 로그인하며 password를 가로채는데
	// 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야 
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음.

	private void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
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
	    	.loginPage("/auth/loginForm") // 로그인 페이지로 보내라.
	    	.loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인해준다. 
	  		.defaultSuccessUrl("/"); // 정상적으로 로그인되면 "/"로 이동
	  
	  return http.build();
	 }
}
