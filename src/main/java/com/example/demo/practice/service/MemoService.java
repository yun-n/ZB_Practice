package com.example.demo.practice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.practice.entity.Memo;
import com.example.demo.practice.model.MemoDto;
import com.example.demo.practice.repository.MemoRepository;
import com.example.demo.practice.service.MemoService_remove;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemoService{
	
	private final MemoRepository memoRepository;

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

	public int insertMemo(MemoDto memoDto) {
		return memoRepository.save(memoDto.toEntity()).getId();
	}

}
