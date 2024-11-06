package com.example.demo;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.example.demo.board.dto.BoardRequestDto;
import com.example.demo.board.dto.BoardResponseDto;
import com.example.demo.board.dto.ResponseDto;
import com.example.demo.board.entity.Board;
import com.example.demo.board.repository.BoardRepository;
import com.example.demo.board.service.BoardService;

@ExtendWith(MockitoExtension.class)
public class BoardServiceMockTest {

//	@Mock: Mock 객체를 만들어 반환해주는 어노테이션
//	@Spy: Stub하지 않은 메서드들은 원본 메서드 그대로 사용하는 어노테이션
//	@InjectMocks: @Mock 또는 @Spy로 생성된 가짜 객체를 자동으로 주입시켜주는 어노테이션

	@InjectMocks
	BoardService boardService;

	@Mock
	BoardRepository boardRepository;

	private Board board;
	private BoardRequestDto reqDto;

	@BeforeEach
	void setUp() {
		reqDto = new BoardRequestDto();
		reqDto.setBoardContent("test");
		reqDto.setBoardTitle("test");
		reqDto.setBoardPassword("test");
		reqDto.setBoardWriter("test");

		board = Board.of(reqDto);

		Mockito.when(boardRepository.save(Mockito.any(Board.class))).thenReturn(board);
	}

	@Test
	void getBoardList() {
		PageRequest pageable = PageRequest.of(0, 3);

		Page<Board> mockPage = new PageImpl<>(List.of(board), pageable, 1);
		Mockito.when(boardRepository.findAllByOrderByCreateDateDesc(pageable)).thenReturn(mockPage);

		boardService.boardRegister(reqDto);
		Page<BoardResponseDto> boardList = boardService.getBoardList(pageable);

		Assertions.assertEquals(mockPage.getTotalElements(), boardList.getTotalElements());
	}

	@Test
	void getBoard() {
		BoardResponseDto boardResDto = boardService.boardRegister(reqDto);

		Mockito.when(boardRepository.findById(boardResDto.getBoardNo())).thenReturn(java.util.Optional.of(board));

		BoardResponseDto findBoard = boardService.getBoard(boardResDto.getBoardNo());

		Assertions.assertEquals(boardResDto.getBoardTitle(), findBoard.getBoardTitle());
	}

	@Test
	void updateBoard() throws Exception {
		BoardResponseDto boardResDto = boardService.boardRegister(reqDto);
		Mockito.when(boardRepository.findById(boardResDto.getBoardNo())).thenReturn(java.util.Optional.of(board));

		reqDto.setBoardTitle("test-modi");
		reqDto.setBoardContent("test-modi-content");
		
		BoardResponseDto updateBoard = boardService.updateBoard(boardResDto.getBoardNo(), reqDto);

		Assertions.assertEquals(reqDto.getBoardTitle(), updateBoard.getBoardTitle());
	}

	@Test
	void boardRegister() {
		BoardResponseDto boardResDto = boardService.boardRegister(reqDto);
		Mockito.when(boardRepository.findById(boardResDto.getBoardNo())).thenReturn(java.util.Optional.of(board));

		BoardResponseDto findBoard = boardService.getBoard(boardResDto.getBoardNo());

		Assertions.assertEquals(boardResDto.getBoardTitle(), findBoard.getBoardTitle());
	}

	@Test
	void deleteBoard() throws Exception {
		BoardResponseDto boardResDto = boardService.boardRegister(reqDto);
		Mockito.when(boardRepository.findById(boardResDto.getBoardNo())).thenReturn(java.util.Optional.of(board));
		
		ResponseDto resDto = boardService.deleteBoard(boardResDto.getBoardNo(), reqDto);

		Assertions.assertEquals(resDto.isSuccess(), true);
	}

}
