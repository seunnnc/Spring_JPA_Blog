package com.se.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.se.blog.service.BoardService;

@Controller
public class BoardController {
	
	//컨트롤러에서 세션을 찾는 방법?
	//세션에 접근하고 싶을 때 메소드명(@AuthenticationPrincipal PrincipalDetail pricipal) 이렇게 접근 
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"","/"})
	public String index(Model model, @PageableDefault(size=8, sort="id", direction=Sort.Direction.DESC) Pageable pageable) {	
		model.addAttribute("boards", boardService.boardList(pageable));
		return "index";	//viewResolver 작동 => 해당 jsp 파일로  model을 들고 이동
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.boardDetail(id));
		return "board/detail";
	}
	
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.boardDetail(id));
		return "board/updateForm";
	}
	
	//user권한 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
