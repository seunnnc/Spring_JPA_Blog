package com.se.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.se.blog.dto.ResponseDto;
import com.se.blog.model.RoleType;
import com.se.blog.model.User;
import com.se.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession hs;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save 호출됨");
		//실제로 DB에 insert를 하고 return 해줌 
		user.setRole(RoleType.USER);
		userService.join(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user) {
		System.out.println("UserApiController : login 호출됨");
		User principal = userService.login(user);	//principal : 접근 주체 라는 뜻 
		
		if(principal != null) {
			hs.setAttribute("principal", principal);
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}
