package com.example.demo.practice.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.practice.model.MemoDto;
import com.example.demo.practice.service.MemoService;
import com.example.demo.practice.service.MemoService_remove;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemoRestController {

	// 생성자 주입
	private final MemoService memoService;
	
	
//	@GetMapping("/memo/register")
//	public String memoRegisterForm( ) {
//		return "memo/register";
//	}
	
	@PostMapping("/memo/register")
	public void memoRegister(MemoDto memoDto) {
//		memoService.insertMemo(memoDto);
//		return "redirect:/memo/list";
		memoService.insertMemo(memoDto);
	}
	
	@GetMapping("/memo/list")
	public List<MemoDto> getMemoList(Model model) {
//        List<MemoDto> memoDtoList = memoService.getMemoList();
//        model.addAttribute("memoList", memoDtoList);
//		return "memo/list";
        return memoService.getMemoList();
	}

}
