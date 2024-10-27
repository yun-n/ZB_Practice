package com.example.demo.board.controller;

import java.util.List;

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
	
	/**
	 * 게시판 목록 조회
	 * @return
	 */
	@GetMapping("/api/boardList")
	public List<BoardResponseDto> getBoardList(){
		return boardService.getBoardList();
	}
	
	/**
	 * 게시판 글 작성
	 * @param boardRegister
	 * @return
	 */
	@PostMapping("/api/boardRegister")
	public BoardResponseDto boardRegister(@RequestBody BoardRequestDto requestDto) {
		System.out.print("boardRegister : " + requestDto.toString());
		return boardService.boardRegister(requestDto);
	}
	
	/**
	 * 게시판 글 조회
	 * @param boardNo
	 * @return
	 */
	@GetMapping("/api/board/{boardNo}")
	public BoardResponseDto getBoard(@PathVariable int boardNo) {
		return boardService.getBoard(boardNo);
	}
	
	/**
	 * 게시판 글 수정
	 * @param boardNo
	 * @param requestDto
	 * @return
	 * @throws Exception 
	 */
	@PutMapping("/api/board/{boardNo}")
	public BoardResponseDto updateBoard(@PathVariable int boardNo, @RequestBody BoardRequestDto requestDto) throws Exception {
		return boardService.updateBoard(boardNo, requestDto);
	}
	
	/**
	 * 게시판 글 삭제
	 * @param boardNo
	 * @param requestDto
	 * @return
	 * @throws Exception 
	 */
	@DeleteMapping("/api/board/{boardNo}")
	public ResponseDto deleteBoard(@PathVariable int boardNo, @RequestBody BoardRequestDto requestDto) throws Exception {
		return boardService.deleteBoard(boardNo, requestDto);
	}

}
