package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.board.dto.BoardRequestDto;
import com.example.demo.board.dto.BoardResponseDto;
import com.example.demo.board.entity.Board;
import com.example.demo.board.repository.BoardRepository;

@SpringBootTest
@Transactional
public class BoardServiceTest {

	@Autowired
	BoardRepository boardRepository;

	@AfterEach
	public void afterEach() {

	}

	@Test
	void getBoardList() {
		List<BoardResponseDto> list = boardRepository.findAllByOrderByCreateDateDesc().stream()
				.map(BoardResponseDto::new).toList();
		int cnt = 7;
		Assertions.assertEquals(cnt, list.size());
	}
	
	@Test
	void boardRegister() {
		//given
		BoardRequestDto temp = new BoardRequestDto();
		temp.setBoardTitle("temp");
		temp.setBoardContent("temp");
		temp.setBoardWriter("temp");
		temp.setBoardPassword("temp");
		//when
		Board board = boardRepository.save(Board.of(temp));
		
		//then 
		Optional<Board> result = boardRepository.findById(board.getBoardNo());
		Assertions.assertEquals(result.get().getBoardNo(), board.getBoardNo());

	}
	
	@Test
	void getBoard() {
		//given
		BoardRequestDto temp = new BoardRequestDto();
		temp.setBoardTitle("temp");
		temp.setBoardContent("temp");
		temp.setBoardWriter("temp");
		temp.setBoardPassword("temp");
		//when
		Board board = boardRepository.save(Board.of(temp));
		//then
		Optional<Board> result = boardRepository.findById(board.getBoardNo());
		Assertions.assertEquals(result.get().getBoardTitle(),temp.getBoardTitle());
	}
	


}
