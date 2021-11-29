package com.bellpro.myblog.repository;

import com.bellpro.myblog.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// JpaRepository 상속 받아서 사용 <엔티티, ID 자료형>
// 공통 인터페이스 사용 가능 (save, delete, findById ...)
public interface BoardRepository extends JpaRepository<Board, Long> {   // Board 테이블 연결
    List<Board> findByOrderByCreatedAtDesc();  // 작성일자 내림차순 정렬
    List<Board> findByTitleContaining(String keyword);  // 게시글 검색
}
