package com.se.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.se.blog.model.User;

//DAO
//자동으로 Bean 등록 
//@Repository 생략가능 
public interface UserRepository extends JpaRepository<User, Integer>{
	
}
