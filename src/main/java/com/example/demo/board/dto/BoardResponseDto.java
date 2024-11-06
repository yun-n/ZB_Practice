package com.example.demo.board.dto;

import java.time.LocalDateTime;

import com.example.demo.board.entity.Board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseDto {

	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;

	@Builder
	public BoardResponseDto(int boardNo, String boardTitle, String boardContent, String boardWriter,
			LocalDateTime createDate, LocalDateTime updateDate) {
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public BoardResponseDto(Board entity) {
		this.boardNo = entity.getBoardNo();
		this.boardTitle = entity.getBoardTitle();
		this.boardContent = entity.getBoardContent();
		this.boardWriter = entity.getBoardWriter();
		this.createDate = entity.getCreateDate();
		this.updateDate = entity.getUpdateDate();
	}

}
