package com.example.demo.practice.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.practice.model.MemoDto;
import com.example.demo.practice.service.MemoService;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemoRestController {

	// 생성자 주입
	private final MemoService memoService;

	
	@PostMapping("/memo/register")
	public void memoRegister(MemoDto memoDto) {

		memoService.insertMemo(memoDto);
	}
	
	@GetMapping("/memo/list")
	public List<MemoDto> getMemoList() { 

        return memoService.getMemoList();
	}

}
