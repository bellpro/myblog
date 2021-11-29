package com.bellpro.myblog.controller;

import com.bellpro.myblog.domain.Board;
import com.bellpro.myblog.dto.BoardRequestDto;
import com.bellpro.myblog.repository.BoardRepository;
import com.bellpro.myblog.security.UserDetailsImpl;
import com.bellpro.myblog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor // 의존성 주입, final 필드에 대해 생성자를 생성,  @Autowired 대신 사용
@RestController          // JSON 데이터를 주고 받는 자동 응답기
public class BoardController {
    private final BoardService boardService;
    private final BoardRepository boardRepository;

    // 게시글 작성
    @PostMapping("/board/write")
    public Board writeBoard(@RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){ // @RequestBody: JSON 변환
        String username = userDetails.getUsername();    // 사용자 인증 확인
        Board board = new Board(boardRequestDto, username); // 게시글 저장
        return boardRepository.save(board);
    }

    // 게시글 삭제
    @DeleteMapping("/board/{id}")
    public Long deleteBoard(@PathVariable Long id){ // @PathVariable: 요청 파라미터 값 전달 받음
        boardRepository.deleteById(id); // 게시글 삭제
        return id;
    }
}

