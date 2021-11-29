package com.bellpro.myblog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter // set 메소드 자동 생성
@Getter // get 메소드 자동 생성
@NoArgsConstructor  // 기본 생성자 자동 생성
public class UserInfoDto {
    @NotBlank(message = "아이디를 입력해주세요.")                     // 필수 입력 설정 (NULL, "", " " 불가)
    @Pattern(regexp = "^[a-zA-Z0-9]{3,}$", message = "알파벳 대소문자(a~z, A~Z) 또는 숫자(0~9)를 포함해서 3자 이상 입력해주세요.")   // 알파벳 대소문자(a~z, A~Z), 숫자(0~9)
    private String username;            // 사용자 아이디

    @NotBlank(message = "패스워드를 입력해주세요.")                    // 필수 입력 설정 (NULL, "", " " 불가)
    @Length(min = 4, message = "패스워드는 최소 4자 이상입니다.")       // 길이 설정
    private String password;            // 사용자 패스워드

    private String passwordConfirm;     // 사용자 패스워드 확인

    @NotBlank(message = "이메일을 입력해주세요.")                     // 필수 입력 설정 (NULL, "", " " 불가)
    @Email(message = "이메일 형식에 맞지 않습니다.")                   // 이메일 형식 설정
    private String email;               // 사용자 이메일
}
