package com.se.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob	//대용량 데이터 쓸때 사용 
	private String content;	//섬머노트 라이브러리 <html>태그 섞여서 디자인이 됨. 
	
	@ColumnDefault("0")
	private int count;	//조회수 

	@ManyToOne	//Many = Board, One = User -> 여러 게시물 한 명의 유저가 쓸 수 있음 
	@JoinColumn(name = "userId")
	private User user;	//DB는 오브젝트 저장할 수 없으니 자바는 저장 가능, FK
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
