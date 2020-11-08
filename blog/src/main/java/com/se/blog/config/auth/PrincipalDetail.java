package com.se.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.se.blog.model.User;

import lombok.Getter;


// 스프링 시큐리티 로그인 요청 가로채서 로그인 진행하고 완료 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션 저장소에 저장 해줌
@Getter
public class PrincipalDetail implements UserDetails{
	private User user;	//컴포지션 
	
	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	//계정이 만료되지 않았는지 return (true : 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정이 잠겨있지 않았는지 return (true : 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//비밀번호 만료되지 않았는지 return (true : 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정이 활성화(사용가능)인지 return (true : 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}

	//계정의 권한을 return (true : 만료안됨) 권한이 여러개면 루프문 돌림
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList();
		
		/*
		collectors.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return "ROLE_" + user.getRole();	//ROLE_USER로 리턴 되어야 스프링에서 확인이 됨,"ROLE_" 이런식으로 꼭 붙여줘야 함
			}
		});
		아래 식 처럼 쓰면 됨
		*/
		
		collectors.add(()->{ return "ROLE_" + user.getRole(); });
		
		return collectors;
	}

	
}
