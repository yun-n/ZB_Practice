package com.example.demo.practice.model;

import com.example.demo.practice.entity.Memo;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class MemoDto {
	
	private int id;
	private String text;
	
	public Memo toEntity() {
		Memo build = Memo.builder().id(id).text(text).build();
		return build;
	}
	
	
	@Builder
	public MemoDto(int id, String text) {
		super();
		this.id = id;
		this.text = text;
	}
	
	

	
	
}
