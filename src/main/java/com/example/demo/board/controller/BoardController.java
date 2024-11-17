package com.example.demo.board.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.board.dto.BoardRequestDto;
import com.example.demo.board.dto.BoardResponseDto;
import com.example.demo.board.dto.ResponseDto;
import com.example.demo.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/api/board/list")
	public Page<BoardResponseDto> getBoardList(@PageableDefault(page = 0, size = 3) Pageable pageable){
		return boardService.getBoardList(pageable);
	}
	
	@PostMapping("/api/board/register")
	public BoardResponseDto boardRegister(@RequestBody BoardRequestDto requestDto) {
		return boardService.boardRegister(requestDto);
	}
	
	@GetMapping("/api/board/{boardNo}")
	public BoardResponseDto getBoard(@PathVariable int boardNo) {
		return boardService.getBoard(boardNo);
	}
	
	@PutMapping("/api/board/{boardNo}")
	public BoardResponseDto updateBoard(@PathVariable int boardNo, @RequestBody BoardRequestDto requestDto) throws Exception {
		return boardService.updateBoard(boardNo, requestDto);
	}
	
	@DeleteMapping("/api/board/{boardNo}")
	public ResponseDto deleteBoard(@PathVariable int boardNo, @RequestBody BoardRequestDto requestDto) throws Exception {
		return boardService.deleteBoard(boardNo, requestDto);
	}

}
