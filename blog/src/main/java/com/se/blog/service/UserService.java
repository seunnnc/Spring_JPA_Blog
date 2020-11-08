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
		String rawPassword = user.getPassword(); // 원래 암호
		String encPassword = encoder.encode(rawPassword); // 해쉬 암호
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
	@Transactional(readOnly = true)
	public User chkUser(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}

	@Transactional
	public void updateUser(User user) {
		// 수정 시 JPA에 User 영속화 시키고 영속화된 User 오브젝트 수정
		// select해서 DB에서 User 오브젝트 가져오는 이유는 영속화 하기 위해서
		// 영속화된 오브젝트 변경하면 DB에 자동으로 update날려줌
		User persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원정보를 찾을 수 없음");
		});
		
		//Validate check => oauth 값이 없으면 수정 가능 
		if(persistance.getOauth() == null || persistance.getOauth().equals("")) {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
		}
		
		//회원수정 함수 종료시(=Service 종료) = 트랜잭션 종료 = 자동으로 commit
		//영속화 된 persistence 객체의 변화 감지되면 더티 체킹하여 update문 날림
	}


}
