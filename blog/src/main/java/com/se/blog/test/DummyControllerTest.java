package com.se.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.se.blog.model.RoleType;
import com.se.blog.model.User;
import com.se.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired	//의존성 주입 
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		
		try {
			userRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			return "삭제 실패! 해당 id DB에 없음!";
		}
		
		return "삭제 완료! id : " + id;
	}
	
	
	//save함수 : id 전달하지 않으면 insert해주고 id 전달하면 해당 id 대한 데이터 있으면 update 없으면 insert해줌 
	//email, password
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {	//JSON 데이터 요청 -> MessageConverter의 Jackson 라이브러리가 변환해서 받아줌
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("수정 실패!");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());

		//userRepository.save(user);
		
		//더티 체킹
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	//한 페이지 당 2건의 데이터 받기 
	@GetMapping("/dummy/user")
	public Page<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return pagingUser;
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
