package com.example.demo;

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
//		Mockito.when(boardRepository.save(Mockito.any(Board.class))).thenReturn(board);
	}

	@Test
	void getBoardList() {
		Mockito.when(boardRepository.save(Mockito.any(Board.class))).thenReturn(board);
		
		PageRequest pageable = PageRequest.of(0, 3);

		Page<Board> mockPage = new PageImpl<>(List.of(board), pageable, 1);
		Mockito.when(boardRepository.findAllByOrderByCreateDateDesc(pageable)).thenReturn(mockPage);

		boardService.boardRegister(reqDto);
		Page<BoardResponseDto> boardList = boardService.getBoardList(pageable);

		Assertions.assertEquals(mockPage.getTotalElements(), boardList.getTotalElements());
	}

	@Test
	void getBoard() {
		Mockito.when(boardRepository.save(Mockito.any(Board.class))).thenReturn(board);
		
		BoardResponseDto boardResDto = boardService.boardRegister(reqDto);

		Mockito.when(boardRepository.findById(boardResDto.getBoardNo())).thenReturn(java.util.Optional.of(board));

		BoardResponseDto findBoard = boardService.getBoard(boardResDto.getBoardNo());

		Assertions.assertEquals(boardResDto.getBoardTitle(), findBoard.getBoardTitle());
	}

	@Test
	void updateBoard() throws Exception {
		Mockito.when(boardRepository.save(Mockito.any(Board.class))).thenReturn(board);
		
		BoardResponseDto boardResDto = boardService.boardRegister(reqDto);
		Mockito.when(boardRepository.findById(boardResDto.getBoardNo())).thenReturn(java.util.Optional.of(board));

		reqDto.setBoardTitle("test-modi");
		reqDto.setBoardContent("test-modi-content");
		
		BoardResponseDto updateBoard = boardService.updateBoard(boardResDto.getBoardNo(), reqDto);

		Assertions.assertEquals(reqDto.getBoardTitle(), updateBoard.getBoardTitle());
	}

	@Test
	void boardRegister() {
		Mockito.when(boardRepository.save(Mockito.any(Board.class))).thenReturn(board);
		
		BoardResponseDto boardResDto = boardService.boardRegister(reqDto);
		Mockito.when(boardRepository.findById(boardResDto.getBoardNo())).thenReturn(java.util.Optional.of(board));

		BoardResponseDto findBoard = boardService.getBoard(boardResDto.getBoardNo());

		Assertions.assertEquals(boardResDto.getBoardTitle(), findBoard.getBoardTitle());
	}

	@Test
	void deleteBoard() throws Exception {
		Mockito.when(boardRepository.save(Mockito.any(Board.class))).thenReturn(board);
		
		BoardResponseDto boardResDto = boardService.boardRegister(reqDto);
		Mockito.when(boardRepository.findById(boardResDto.getBoardNo())).thenReturn(java.util.Optional.of(board));
		
		ResponseDto resDto = boardService.deleteBoard(boardResDto.getBoardNo(), reqDto);

		Assertions.assertEquals(resDto.isSuccess(), true);
	}
	
	@Test
	void getBoardNotFoundException() {
		int invalidBoardNo = 999;
		Mockito.when(boardRepository.findById(invalidBoardNo)).thenReturn(java.util.Optional.empty());
		
		Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> boardService.getBoard(invalidBoardNo));
		
		Assertions.assertEquals("글번호가 존재하지 않습니다.", e.getMessage());
	}
	
	@Test
	void updateBoardPasswordMismatchException() throws Exception {
		Mockito.when(boardRepository.save(Mockito.any(Board.class))).thenReturn(board);
		
	    BoardResponseDto boardResDto = boardService.boardRegister(reqDto);
	    Mockito.when(boardRepository.findById(boardResDto.getBoardNo())).thenReturn(java.util.Optional.of(board));

	    reqDto.setBoardPassword("wrong-password");

	    Exception exception = Assertions.assertThrows(Exception.class, 
	        () -> boardService.updateBoard(boardResDto.getBoardNo(), reqDto));
	    Assertions.assertEquals("비밀번호가 일치하지 않습니다.", exception.getMessage());
	}

	@Test
	void updateBoardNotFoundException() {
	    int invalidBoardNo = 999;
	    Mockito.when(boardRepository.findById(invalidBoardNo)).thenReturn(java.util.Optional.empty());

	    Exception exception = Assertions.assertThrows(IllegalArgumentException.class, 
	        () -> boardService.updateBoard(invalidBoardNo, reqDto));
	    Assertions.assertEquals("글번호가 존재하지 않습니다.", exception.getMessage());
	}
	
	@Test
	void deleteBoardPasswordMismatchException() {
		Mockito.when(boardRepository.save(Mockito.any(Board.class))).thenReturn(board);
		
	    BoardResponseDto boardResDto = boardService.boardRegister(reqDto);
	    Mockito.when(boardRepository.findById(boardResDto.getBoardNo())).thenReturn(java.util.Optional.of(board));

	    reqDto.setBoardPassword("wrong-password");

	    Exception exception = Assertions.assertThrows(Exception.class, 
	        () -> boardService.deleteBoard(boardResDto.getBoardNo(), reqDto));
	    Assertions.assertEquals("비밀번호가 일치하지 않습니다.", exception.getMessage());
	}

	@Test
	void deleteBoardNotFoundException() {
	    int invalidBoardNo = 999;
	    Mockito.when(boardRepository.findById(invalidBoardNo)).thenReturn(java.util.Optional.empty());

	    Exception exception = Assertions.assertThrows(IllegalArgumentException.class, 
	        () -> boardService.deleteBoard(invalidBoardNo, reqDto));
	    Assertions.assertEquals("글 번호가 존재하지 않습니다.", exception.getMessage());
	}

}
