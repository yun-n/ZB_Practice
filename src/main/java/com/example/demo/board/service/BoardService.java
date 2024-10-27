package com.example.demo.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.board.dto.BoardRequestDto;
import com.example.demo.board.dto.BoardResponseDto;
import com.example.demo.board.dto.ResponseDto;
import com.example.demo.board.entity.Board;
import com.example.demo.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	@Transactional
	public List<BoardResponseDto> getBoardList(){
		return boardRepository.findAllByOrderByCreateDateDesc().stream().map(BoardResponseDto::new).toList();
	}
	
	@Transactional
	public BoardResponseDto boardRegister(BoardRequestDto requestDto) {
		Board board = boardRepository.save(Board.of(requestDto));
	    return new BoardResponseDto(board);
	}
	
	@Transactional
	public BoardResponseDto getBoard(int boardNo) {
		return boardRepository.findById(boardNo).map(BoardResponseDto::new).orElseThrow(() -> new IllegalArgumentException("글번호가 존재하지 않습니다.") );
	}
	
	@Transactional
	public BoardResponseDto updateBoard(int boardNo, BoardRequestDto requestDto) throws Exception{
		Board board = boardRepository.findById(boardNo).orElseThrow(()-> new IllegalArgumentException("글번호가 존재하지 않습니다."));
		
		if(!requestDto.getBoardPassword().equals(board.getBoardPassword())) {
			throw new Exception("비밀번호가 일치하지 않습니다.");
		}
		
		board.update(requestDto);
		return new BoardResponseDto(board);
	}
	
	@Transactional
	public ResponseDto deleteBoard(int boardNo, BoardRequestDto requestDto) throws Exception {
		
		Board board = boardRepository.findById(boardNo).orElseThrow(
				() -> new IllegalArgumentException("글 번호가 존재하지 않습니다."));
	
		if(!requestDto.getBoardPassword().equals(board.getBoardPassword())) {
			throw new Exception("비밀번호가 일치하지 않습니다.");
		}
		
		boardRepository.deleteById(boardNo);
		return new ResponseDto(true);
	
	}
	
	

}
