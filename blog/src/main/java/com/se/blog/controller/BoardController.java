package com.se.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BoardController {
	
	//세션에 접근하고 싶을 때 메소드명(@AuthenticationPrincipal PrincipalDetail pricipal) 이렇게 접근 

	@GetMapping({"","/"})
	public String index() {	//컨트롤러에서 세션을 찾는 방법?
		
		return "index";
	}
	
	//user권한 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
