package com.se.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//인증 안된 사용자 출입 할 수 있는 경로 /auth/** 만 허용
//그냥 주소가 /면 index.jsp 허용
//static이하의 /js/**, /css/**, /img/** 허용 

@Controller
public class UserController {

	@GetMapping("/auth/joinForm")
	public String joinForm() {
		
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		
		return "user/loginForm";
	}
	
}
