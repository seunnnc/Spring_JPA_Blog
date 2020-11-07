package com.se.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Bean 등록 : Spring Container에서 객체를 관리할 수 있게 하는 것 
@Configuration	//Bean 등록 (IoC로 관리)
@EnableWebSecurity	//시큐리티 필터가 등록이 된다
@EnableGlobalMethodSecurity(prePostEnabled = true)	//특정 주소로 접근하면 권한 및 인증을 미리 체크 하겠음
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean	//IoC가 됨!
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()	//csrf 토큰 비활성화 (테스트시 걸어두는 것이 좋음)
			.authorizeRequests()
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/img/**")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/loginForm");
	}
	
}
