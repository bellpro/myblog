package com.bellpro.myblog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class PostRequestDto {
    private String title;
    private String content;
    private String writer;
}
