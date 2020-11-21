package com.se.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	private String content;	//섬머노트 라이브러리
	
	private int count;	//조회수 

	@ManyToOne(fetch = FetchType.EAGER)	//Many = Board, One = User -> 여러 게시물 한 명의 유저가 쓸 수 있음 
	@JoinColumn(name = "userId")
	private User user;	//DB는 오브젝트 저장할 수 없으니 자바는 저장 가능, FK
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)	//mappedBy : 연관관의 주인이 아니다(FK가 아님) DB에 컬럼 만들지X 
	@JsonIgnoreProperties({"board"})
	@OrderBy("id desc")
	private List<Reply> replies;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
