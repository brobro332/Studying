package com.cos.blog.model;

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

// @DynamicInsert // insert시에 null인 필드를 제외시켜준다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//ORM -> Java(또는 모든 언어) Object -> 테이블로 매핑해주는 기술
@Entity // User 클래스가 MariaDB에 테이블이 생성이 된다.
public class User {
	
	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 오라클 : 시퀀스, MySQL 또는 MariaDB : auto_increment
	
	@Column(nullable = false, length = 30, unique = true)
	private String username; // 아이디
	
	@Column(nullable = false, length = 100) // length 값을 크게 주는 이유 : 해쉬(비밀번호 암호화)
	private String password;
	
	@Column(nullable = false, length = 100)
	private String email;
	
	// @ColumnDefault("user")
	// DB는 ROLE Type이라는게 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋다. // ADMIN, USER
	
	private String oauth; // kakao, google
	
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate;
}
