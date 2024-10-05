package com.example.demo.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.practice.entity.Memo;

// 데이터베이스와 연동하는 코드는 보통 repository에 작성
@Repository
public interface MemoRepository extends JpaRepository<Memo, Integer>{ //클래스, 키

}
