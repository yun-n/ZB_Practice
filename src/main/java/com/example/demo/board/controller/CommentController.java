package com.example.demo.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.board.dto.CommentRequest;
import com.example.demo.board.dto.ResponseDto;
import com.example.demo.board.entity.Comment;
import com.example.demo.board.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commnetService;
	
	@GetMapping("/api/board/{boardNo}/comment")
	public List<Comment> getcommentList(@PathVariable int boardNo){
		return commnetService.getCommentList(boardNo);
	}
	
	@PostMapping("/api/board/{boardNo}/comment")
	public Comment commentRegister(@PathVariable int boardNo, @RequestBody CommentRequest request) {
		Comment saveComment = commnetService.commentRegister(boardNo, request);
		return  saveComment;
	}
	
	@PutMapping("/api/board/{boardNo}/comment/{commentNo}")
	public Comment updateComment(@PathVariable int boardNo, @PathVariable int commentNo, @RequestBody CommentRequest request) {
		return commnetService.updateComment(boardNo, commentNo, request);	
	}
	
	@DeleteMapping("/api/board/{boardNo}/comment/{commentNo}")
	public ResponseDto deleteComment(@PathVariable int boardNo, @PathVariable int commentNo) {
		return commnetService.deleteComment(boardNo, commentNo);		
	}

}
