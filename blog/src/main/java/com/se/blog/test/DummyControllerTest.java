package com.se.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.se.blog.model.RoleType;
import com.se.blog.model.User;
import com.se.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired	//의존성 주입 
	private UserRepository userRepository;
	
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	//한 페이지 당 2건의 데이터 받기 
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	//{id}주소로 파라미터 전달 받을 수 있음 
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		
//		람다식 
//		User user = userRepository.findById(id).orElseThrow(() -> {
//			return new IllegalArgumentException("해당 사용자는 없습니다.");
//		});
			
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 사용자는 없습니다.");
			}
		});
		return user;
	}
	
	@PostMapping("/dummy/join")
	public String join(User user){
		
		
		System.out.println("id : " + user.getId());
		System.out.println("username : "+ user.getUsername());
		System.out.println("password : "+ user.getPassword());
		System.out.println("email : "+ user.getEmail());
		System.out.println("role : "+ user.getRole());
		System.out.println("createDate : "+ user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "가입 완료!";
	}
}
