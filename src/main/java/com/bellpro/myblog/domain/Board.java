package com.bellpro.myblog.domain;

import com.bellpro.myblog.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // get 메소드 자동 생성
@NoArgsConstructor  // 기본 생성자 자동 생성
@Entity     // DB 테이블 설정
public class Board extends Timestamped{ // 생성날짜/수정날짜 상속 받음
    @Id // 기본키 설정
    @GeneratedValue(strategy = GenerationType.AUTO)  // 자동으로 인덱스 증가
    private Long id;

    @Column(nullable = false)                   // 열 설정 (무조건 입력)
    private String author;                      // 게시글 작성자

    @Column(nullable = false)                   // 열 설정 (무조건 입력)
    private String title;                       // 게시글 제목

    @Column(nullable = false)                   // 열 설정 (무조건 입력)
    private String content;                     // 게시글 내용

    // 게시판 dto 생성자
    public Board(BoardRequestDto boardRequestDto, String username){
        this.author = username;
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
    }
}
