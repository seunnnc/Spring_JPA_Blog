package com.se.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.se.blog.model.User;

//DAO
//자동으로 Bean 등록 
//@Repository 생략가능 
public interface UserRepository extends JpaRepository<User, Integer>{
	
	//SELECT * FROM user WHERE username = 1?;
	Optional<User> findByUsername(String username);

}

//JPA Naming 쿼리
	//SELECT * FROM user WHERE username = ? AND password = ?; 가 동작함
	//User findByUsernameAndPassword(String username, String password);
	
	//@Query(value="SELECT * FROM user WHERE username = ? AND password = ?", nativeQuery = true)
	//User login(String username, String password);

