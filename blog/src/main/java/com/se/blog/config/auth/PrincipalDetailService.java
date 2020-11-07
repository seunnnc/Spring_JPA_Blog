package com.se.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.se.blog.model.User;
import com.se.blog.repository.UserRepository;

@Service	//Bean등록
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	//스프링이 로그인 요청을 가로챌 때, username, password 2개 가로챔 
	//password 처리 알아서 함, 해당 username DB에 있는지 확인만 하면 됨 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
				});
		return new PrincipalDetail(principal);	//시큐리티 세션에 유저 정보 저장됨
	}
	
}
