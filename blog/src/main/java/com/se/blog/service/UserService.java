package com.se.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional(readOnly = true)	//select 할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
	public User login(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}

}
