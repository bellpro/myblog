package com.bellpro.myblog.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter // set 메소드 자동 생성
@Getter // get 메소드 자동 생성
@RequiredArgsConstructor    // 의존성 주입, final 필드에 대해 생성자를 생성,  @Autowired 대신 사용
public class BoardRequestDto {
    private final String author;    // 게시글 작성자
    private final String title;     // 게시글 제목
    private final String content;   // 게시글 내용
}
