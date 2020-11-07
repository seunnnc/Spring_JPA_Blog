package com.se.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.se.blog.model.RoleType;
import com.se.blog.model.User;
import com.se.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔 통해서 Bean에 등록을 해준다. IoC를 해준다
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	// 회원가입
	@Transactional
	public void join(User user) {
		String rawPassword = user.getPassword();	//원래 암호
		String encPassword = encoder.encode(rawPassword);	//해쉬 암호
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}

}
