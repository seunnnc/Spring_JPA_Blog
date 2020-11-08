package com.se.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.se.blog.model.Board;
import com.se.blog.model.User;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	
}

