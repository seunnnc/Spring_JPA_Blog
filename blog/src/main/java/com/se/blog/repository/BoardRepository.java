package com.se.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.se.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	
}

