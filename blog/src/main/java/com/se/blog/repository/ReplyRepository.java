package com.se.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.se.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{

}
