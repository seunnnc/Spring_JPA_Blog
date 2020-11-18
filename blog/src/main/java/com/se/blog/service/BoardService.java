package com.se.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.se.blog.model.Board;
import com.se.blog.model.User;
import com.se.blog.repository.BoardRepository;

//스프링이 컴포넌트 스캔 통해서 Bean에 등록을 해준다. IoC를 해준다
@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public void write(Board board, User user) {	//title, content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public Page<Board> boardList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board boardDetail(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("없는 게시물 입니다.");
				});
	}

	@Transactional
	public void deleteBoard(int id) {
		System.out.println("글삭제하기 : "+id);
		boardRepository.deleteById(id);
	}

	@Transactional
	public void updateBoard(int id, Board requestBoard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("없는 게시물 입니다.");
				});	//영속화 시키는것 
		
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		//해당 함수 종료시(=Service 종료될 때) 트랜잭션 종료, 더티 체킹 일어남 - 자동 업데이트 db flush
	}
}
