package com.example.demo.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.board.dto.CommentRequest;
import com.example.demo.board.dto.ResponseDto;
import com.example.demo.board.entity.Board;
import com.example.demo.board.entity.Comment;
import com.example.demo.board.repository.BoardRepository;
import com.example.demo.board.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {

	private final CommentRepository commentRepository;
	private final BoardRepository boardRepository;

	@Transactional(readOnly = true)
	public List<Comment> getCommentList(int boardNo) {
		Board board = boardRepository.findById(boardNo)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. " + boardNo));

		List<Comment> commentList = board.getComments();

		return commentList;
	}

	public Comment commentRegister(int boardNo, CommentRequest request) {
		Board board = boardRepository.findById(boardNo)
				.orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다. " + boardNo));

		request.setBoard(board);

		return commentRepository.save(request.toEntity());
	}

	@Transactional
	public Comment updateComment(int boardNo, int commentNo, CommentRequest request) {
		Board board = boardRepository.findById(boardNo)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. " + boardNo));

		Comment comment = commentRepository.findByBoardAndCommentNo(board, commentNo)
				.orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. " + boardNo + " - " + commentNo));

		comment.update(request.getCommentConetent());

		return new Comment(comment);
	}

	public ResponseDto deleteComment(int boardNo, int commentNo) {
		Board board = boardRepository.findById(boardNo)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. " + boardNo));

		Comment comment = commentRepository.findByBoardAndCommentNo(board, commentNo)
				.orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. " + boardNo + " - " + commentNo));

		commentRepository.delete(comment);
		return new ResponseDto(true);
	}

}
