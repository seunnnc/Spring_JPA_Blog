package com.se.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.se.blog.model.Board;
import com.se.blog.model.RoleType;
import com.se.blog.model.User;
import com.se.blog.repository.BoardRepository;
import com.se.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔 통해서 Bean에 등록을 해준다. IoC를 해준다
@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	// 회원가입
	@Transactional
	public void write(Board board, User user) {	//title, content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

}
