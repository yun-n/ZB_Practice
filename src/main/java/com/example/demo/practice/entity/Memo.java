package com.example.demo.practice.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Memo {

	@GeneratedValue(strategy = GenerationType.IDENTITY) // 기본적인 키생성을 데이터베이스에게 맡김
	@Id
	private int id;

	private String text;

	 @Builder
	public Memo(int id, String text) {
		super();
		this.id = id;
		this.text = text;
	}
	 
	 
	public Memo toEntity() {
		Memo build = Memo.builder().id(id).text(text).build();
		return build;
	}
	

}
