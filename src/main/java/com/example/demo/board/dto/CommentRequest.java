package com.example.demo.board.dto;

import java.time.LocalDateTime;

import com.example.demo.board.entity.Board;
import com.example.demo.board.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentRequest {
	
	private int CommentNo;
	private String commentConetent;
	private LocalDateTime createdDate;
	private LocalDateTime updateDate;
	private String commentPassword;
	private String commentWriter;
	private Board board;
	
	public Comment toEntity() {
		return Comment.builder()
				.commentConetent(commentConetent)
				.createdDate(createdDate)
				.updateDate(updateDate)
				.commentPassword(commentPassword)
				.commentWriter(commentWriter)
				.board(board)
				.build();
	}

}
