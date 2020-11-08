package com.se.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
//@DynamicInsert	insert시에 null인 필드 제외
public class User {
	
	@Id	//Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//프로젝트에서 연결된 DB 넘버링 전략 따라감
	private int id;	//auto_increment
	
	@Column(nullable = false, length = 100, unique=true)
	private String username;	//아이디 
	
	@Column(nullable = false, length = 100)	//비밀번호 암호화떄문에 100
	private String password;	//비밀번호 
	
	@Column(nullable = false, length = 50)
	private String email; 	//이메일
	
	//DB는 RoleType없음 
	@Enumerated(EnumType.STRING)
	private RoleType role;	//Enum 쓰는것이 좋음 //ADMIN, USER
	
	
	private String oauth;	//소셜 로그인/일반 로그인 구별해주기 위해 
	
	@CreationTimestamp	//시간 자동 입력
	private Timestamp createDate;
}
