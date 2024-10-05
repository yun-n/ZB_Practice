package com.example.demo.practice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.practice.entity.Memo;
import com.example.demo.practice.model.MemoDto;
import com.example.demo.practice.repository.MemoRepository;
import com.example.demo.practice.service.MemoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemoServiceImpl implements MemoService{
	
	private final MemoRepository memoRepository;

	@Override
	public List<MemoDto> getMemoList() {
        List<Memo> memoList = memoRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<MemoDto> memoDtoList = new ArrayList<>();

        for(Memo memo : memoList) {
        	MemoDto memoDto = MemoDto.builder()
                    .id(memo.getId())
                    .text(memo.getText())

                    .build();
        	memoDtoList.add(memoDto);
        }
        return memoDtoList;
    }

	@Override
	public int insertMemo(MemoDto memoDto) {
		return memoRepository.save(memoDto.toEntity()).getId();
	}

}
