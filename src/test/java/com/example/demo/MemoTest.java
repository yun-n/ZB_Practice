package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.practice.entity.Memo;
import com.example.demo.practice.repository.MemoRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
public class MemoTest {
	
	@Autowired
	MemoRepository memoReposity;
	
	
	@Test
	void insertMemoTest() {
		//given(준비) : 데이터 준비
		Memo newMemo = new Memo(10,"testtttt");
		//when(실행) : 함수 실행
		memoReposity.save(newMemo);
		//then(검증) : 결과
		List<Memo> memoList = memoReposity.findAll();
		assertTrue(memoList.size()>0);
		
	}
	
	@Test
	void findbyIdTest() {
		//givev
		Memo newMemo = new Memo(11,"junit");
		//when
		Memo memo = memoReposity.save(newMemo);
		//then
		Optional<Memo> result = memoReposity.findById(memo.getId());
		assertEquals(result.get().getText(), "junit");
	}

}
