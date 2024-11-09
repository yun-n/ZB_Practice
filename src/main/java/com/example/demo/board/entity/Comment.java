package com.example.demo.board.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "comments")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentNo;

	private String commentConetent;

	@CreatedDate
	private LocalDateTime createdDate;

	@LastModifiedDate
	private LocalDateTime updateDate;

	private String commentPassword;

	private String commentWriter;

	@ManyToOne
	@JoinColumn(name = "boardNo")
	private Board board;

	// 댓글 수정
	public void update(String commentConetent) {
		this.commentConetent = commentConetent;
	}

	public Comment(Comment comment) {
		this.commentNo = comment.commentNo;
		this.commentConetent = comment.commentConetent;
		this.commentWriter = comment.commentWriter;
		this.createdDate = comment.createdDate;
		this.updateDate = comment.updateDate;

	}
}