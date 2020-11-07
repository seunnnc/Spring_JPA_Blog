package com.se.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se.blog.model.User;
import com.se.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔 통해서 Bean에 등록을 해준다. IoC를 해준다
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	// 회원가입
	@Transactional
	public void join(User user) {
		userRepository.save(user);
	}

}
