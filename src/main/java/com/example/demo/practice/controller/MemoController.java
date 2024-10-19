package com.example.demo.practice.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.practice.model.MemoDto;
import com.example.demo.practice.service.MemoService_remove;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemoController {

//	// 생성자 주입
//	private final MemoService_remove memoService;
//	
//	
//	@GetMapping("/memo/register")
//	public String memoRegisterForm( ) {
//		return "memo/register";
//	}
//	
//	@PostMapping("/memo/register")
//	public String memoRegister(MemoDto memoDto) {
//		memoService.insertMemo(memoDto);
//		return "redirect:/memo/list";
//	}
//	
//	@GetMapping("/memo/list")
//	public String getMemoList(Model model) {
//        List<MemoDto> memoDtoList = memoService.getMemoList();
//        model.addAttribute("memoList", memoDtoList);
//		return "memo/list";
//	}

}
