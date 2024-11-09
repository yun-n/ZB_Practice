package com.example.demo.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.board.entity.Board;
import com.example.demo.board.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	Optional<Comment> findByBoardAndCommentNo(Board board, int commentNo);

}
