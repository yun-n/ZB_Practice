package com.example.demo.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
@NoArgsConstructor
public class BoardRequestDto {

	private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private String boardPassword;
}
