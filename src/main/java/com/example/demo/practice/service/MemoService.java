package com.example.demo.practice.service;

import java.util.List;

import com.example.demo.practice.model.MemoDto;

public interface MemoService {

	List<MemoDto> getMemoList();

	int insertMemo(MemoDto memoDto);

}