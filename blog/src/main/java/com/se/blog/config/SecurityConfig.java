package com.se.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.se.blog.config.auth.PrincipalDetail;
import com.se.blog.config.auth.PrincipalDetailService;

//Bean 등록 : Spring Container에서 객체를 관리할 수 있게 하는 것 
@Configuration	//Bean 등록 (IoC로 관리)
@EnableWebSecurity	//시큐리티 필터가 등록이 된다
@EnableGlobalMethodSecurity(prePostEnabled = true)	//특정 주소로 접근하면 권한 및 인증을 미리 체크 하겠음
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean	//IoC가 됨!
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대산 로그인 할 때 password 가로채기 하는데 
	// 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야 같은 해쉬로 암호화 해서 DB에 있는 해쉬랑 비교할 수 있음 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()	//csrf 토큰 비활성화 (테스트시 걸어두는 것이 좋음)
			.authorizeRequests()
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/img/**", "/dummy/**")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc")	//스프링 시쿠리티 해당 주소로 오는 로그인 가로채서 대신 로그인 해줌 
				.defaultSuccessUrl("/");
	}
	
}
