package com.bellpro.myblog.domain.dto;

import com.bellpro.myblog.domain.post.Post;
import lombok.Getter;

@Getter
public class PostSimpleResponseDto {
    private Long id;
    private String title;
    private String writer;

    public PostSimpleResponseDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.writer = post.getWriter();

    }
}
