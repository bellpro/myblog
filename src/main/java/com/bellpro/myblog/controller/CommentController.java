package com.bellpro.myblog.controller;

import com.bellpro.myblog.domain.Comment;
import com.bellpro.myblog.dto.CommentRequestDto;
import com.bellpro.myblog.repository.CommentRepository;
import com.bellpro.myblog.security.UserDetailsImpl;
import com.bellpro.myblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor    // 의존성 주입, final 필드에 대해 생성자를 생성, @Autowired 대신 사용
@Controller // 자동 응답기, controller 명시(@Component 포함): Bean 등록
public class CommentController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/board/detail/comment")
    @ResponseBody
    public String addComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            return "회원이 아닙니다.";
        }
        String username = userDetails.getUsername();
        Comment comment = new Comment(commentRequestDto, username);
        commentRepository.save(comment);
        return "save";
    }

    // 댓글 수정
    @PutMapping("/comment/{id}")
    @ResponseBody
    public String updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto) {
        commentService.updateComment(id, commentRequestDto);
        return "update";
    }

    // 댓글 삭제 API
    @DeleteMapping("/comment/{id}")
    @ResponseBody
    public Long deleteCommet(@PathVariable Long id) {
        commentRepository.deleteById(id); // 해당 댓글 삭제하기
        return id;
    }
}
