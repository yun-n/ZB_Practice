package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.data.domain.Pageable;
import com.example.demo.board.dto.BoardRequestDto;
import com.example.demo.board.dto.BoardResponseDto;
import com.example.demo.board.dto.ResponseDto;
import com.example.demo.board.entity.Board;
import com.example.demo.board.repository.BoardRepository;
import com.example.demo.board.service.BoardService;

import jakarta.transaction.Transactional;

import java.util.List;

import org.junit.jupiter.api.Assertions;

@SpringBootTest
@Transactional
//@TestPropertySource(properties = {"spring.config.location = classpath:application-test.properties"})
public class BoardServiceIntegrationTest {

	@Autowired
	BoardService boardService;
	
	@Autowired
	BoardRepository boardRepository;
	
	@Test
	void getBoardList() {
		PageRequest pageable = PageRequest.of(0, 3);
		
		BoardRequestDto reqDto = new BoardRequestDto();
		reqDto.setBoardContent("test");
		reqDto.setBoardTitle("test");
		reqDto.setBoardPassword("test");
		reqDto.setBoardWriter("test");
		BoardResponseDto boardResDto  = boardService.boardRegister(reqDto);
		Page<Board> board = boardRepository.findAll(pageable);
		
		Page<BoardResponseDto> boardList = boardService.getBoardList(pageable);
		
		Assertions.assertEquals(board.getTotalElements(),boardList.getTotalElements());
	}
	
	@Test 
	void getBoard() {
		BoardRequestDto reqDto = new BoardRequestDto();
		reqDto.setBoardContent("test");
		reqDto.setBoardTitle("test");
		reqDto.setBoardPassword("test");
		reqDto.setBoardWriter("test");
		BoardResponseDto boardResDto  = boardService.boardRegister(reqDto);
		
		BoardResponseDto findBoard = boardService.getBoard(boardResDto.getBoardNo());
		
		Assertions.assertEquals(boardResDto.getBoardTitle(), findBoard.getBoardTitle());
	}
	
	@Test
	void updateBoard() throws Exception {
		BoardRequestDto reqDto = new BoardRequestDto();
		reqDto.setBoardContent("test");
		reqDto.setBoardTitle("test");
		reqDto.setBoardPassword("test");
		reqDto.setBoardWriter("test");
		BoardResponseDto boardResDto  = boardService.boardRegister(reqDto);
		
		reqDto.setBoardTitle("test-modi");
		reqDto.setBoardContent("test-modi-content");;
		BoardResponseDto updateBoard = boardService.updateBoard(boardResDto.getBoardNo(), reqDto);
		
		Assertions.assertEquals(reqDto.getBoardTitle(), updateBoard.getBoardTitle());
	}
	
	@Test
	void boardRegister() {
		BoardRequestDto reqDto = new BoardRequestDto();
		reqDto.setBoardContent("test");
		reqDto.setBoardTitle("test");
		reqDto.setBoardPassword("test");
		reqDto.setBoardWriter("test");
		BoardResponseDto boardResDto  = boardService.boardRegister(reqDto);
		
		BoardResponseDto findBoard = boardService.getBoard(boardResDto.getBoardNo());
		
		Assertions.assertEquals(boardResDto.getBoardTitle(), findBoard.getBoardTitle());
	}
	
	@Test
	void deleteBoard() throws Exception {
		BoardRequestDto reqDto = new BoardRequestDto();
		reqDto.setBoardContent("test");
		reqDto.setBoardTitle("test");
		reqDto.setBoardPassword("test");
		reqDto.setBoardWriter("test");
		BoardResponseDto boardResDto  = boardService.boardRegister(reqDto);
		
		ResponseDto resDto  = boardService.deleteBoard(boardResDto.getBoardNo(), reqDto);
		
		Assertions.assertEquals(resDto.isSuccess(), true);
	}
}
