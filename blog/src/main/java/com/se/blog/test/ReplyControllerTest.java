package com.se.blog.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.se.blog.model.Board;
import com.se.blog.model.Reply;
import com.se.blog.repository.BoardRepository;
import com.se.blog.repository.ReplyRepository;

@RestController
public class ReplyControllerTest {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@GetMapping("/test/board/{id}")
	public Board getBoard(@PathVariable int id) {
		return boardRepository.findById(id).get();	//Jackson라이브러리 - 오브젝트 json으로 리턴 => 모델의 getter 호출
	}
	
	@GetMapping("/test/reply")
	public List<Reply> getReply() {
		return replyRepository.findAll();	//Jackson라이브러리 - 오브젝트 json으로 리턴 => 모델의 getter 호출
	}
}
