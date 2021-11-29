package com.bellpro.myblog.repository;

import com.bellpro.myblog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

// JpaRepository 상속 받아서 사용 <엔티티, ID 자료형>
// 공통 인터페이스 사용 가능 (save, delete, findById ...)
public interface CommentRepository extends JpaRepository<Comment, Long> {   // Comment 테이블 연결
    List<Comment> findByBoardIdOrderByCreatedAtDesc(Long boardId); // 하나의 게시글에 있는 댓글들 찾기

    @Transactional
    void deleteAllByBoardId(Long boardId);    //게시글 삭제 시 댓글도 모두 삭제

}
