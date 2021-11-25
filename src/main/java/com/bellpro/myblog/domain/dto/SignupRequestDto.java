package com.bellpro.myblog.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data // 기본 생성자 생성, get 메소드, set 메소드 포함
public class SignupRequestDto {
    @NotBlank(message = "닉네임을 입력해주세요.") // 무조건 입력 (NULL, "", " " 불가)
    @Length(min = 3, message = "닉네임은 최소 3자 이상입니다.") //  최소 3자 이상
    @Pattern(regexp = "^[a-zA-Z0-9]{3,}$", message = "알파벳 대소문자(a~z, A~Z), 숫자(0~9)를 입력해주세요.")    // 알파벳 대소문자(a~z, A~Z), 숫자(0~9)
    private String username;            // 닉네임

    @NotBlank(message = "비밀번호를 입력해주세요.") // 무조건 입력 (NULL, "", " " 불가)
    @Length(min = 4, message = "비밀번호는 최소 4자 이상입니다.")    // 최소 4자 이상
    private String password;           // 비밀번호

    @NotBlank // 무조건 입력 (NULL, "", " " 불가)
    @Length(min = 4, message = "비밀번호는 최소 4자 이상입니다.")    // 최소 4자 이상
    private String passwordConfirm;    // 비밀번호 확인

}
