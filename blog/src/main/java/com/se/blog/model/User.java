package com.se.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ORM : JAVA (다른 언어 포함) Object -> 테이블로 매핑해주는 기술임 

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity	//User클래스가 MySQL에 테이블 생성 
public class User {
	
	@Id	//Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//프로젝트에서 연결된 DB 넘버링 전략 따라감
	private int id;	//auto_increment
	
	@Column(nullable = false, length = 30)
	private String username;	//아이디 
	
	@Column(nullable = false, length = 100)	//비밀번호 암호화떄문에 100
	private String password;	//비밀번호 
	
	@Column(nullable = false, length = 50)
	private String email; 	//이메일
	
	@ColumnDefault("'user'")
	private String role;	//Enum 쓰는것이 좋음
	
	@CreationTimestamp	//시간 자동 입력
	private Timestamp createDate;
}
