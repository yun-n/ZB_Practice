package com.example.demo.board.dto;

import lombok.Getter;

@Getter
public class ResponseDto {
	
	private boolean success;

	public ResponseDto(boolean success) {
		this.success = success;
	}
}
