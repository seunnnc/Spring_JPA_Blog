package com.se.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.se.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {
	
	//세션에 접근하고 싶을 때 메소드명(@AuthenticationPrincipal PrincipalDetail pricipal) 이렇게 접근 

	@GetMapping({"","/"})
	public String index(@AuthenticationPrincipal PrincipalDetail pricipal) {	//컨트롤러에서 세션을 찾는 방법?
		
		System.out.println("로그인 사용자 아이디 : " + pricipal.getUsername());
		return "index";
	}
}
