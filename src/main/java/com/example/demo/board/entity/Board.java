package com.example.demo.board.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demo.board.dto.BoardRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Board {

	@GeneratedValue(strategy = GenerationType.IDENTITY) // 기본적인 키생성을 데이터베이스에게 맡김
	@Id
	private int boardNo;

	private String boardTitle;

	private String boardContent;

	private String boardWriter;

	private String boardPassword;

	@CreatedDate
	private LocalDateTime createDate;

	@LastModifiedDate
	private LocalDateTime updateDate;

	@Builder
	public Board(BoardRequestDto requestDto) {
		this.boardTitle = requestDto.getBoardTitle();
		this.boardContent = requestDto.getBoardContent();
		this.boardWriter = requestDto.getBoardWriter();
		this.boardPassword = requestDto.getBoardPassword();
	}

	public void update(BoardRequestDto requestDto) {
		this.boardTitle = requestDto.getBoardTitle();
		this.boardContent = requestDto.getBoardContent();

	}

	public static Board of(BoardRequestDto requestDto) {
		return Board.builder()
				.requestDto(requestDto)
				.build();
	}

}
