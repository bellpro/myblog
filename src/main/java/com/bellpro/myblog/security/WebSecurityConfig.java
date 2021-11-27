package com.bellpro.myblog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true) // @Secured 어노테이션 활성화
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // CSRF protection 을 비활성화
        http.csrf().disable();

        http.authorizeRequests()
                // image, css 폴더를 login 없이 허용
                .antMatchers("/images/**", "/css/**").permitAll()
                // 회원 가입, 로그인 폴더를 login 없이 허용
                .antMatchers("/user/**").permitAll()
                // 회원 가입, 로그인 API login 없이 허용
                .antMatchers("/signup", "/login/**").permitAll()
                // 게시글 관리 폴더를 login 없이 허용
                .antMatchers("/posts/**").permitAll()
                // 게시글 관리 처리 API 허용
                .antMatchers("/posts/detail", "/api/posts", "/api/posts/**" ).permitAll()
                .antMatchers("/" ).permitAll()
                // 그 외 어떤 요청이든 '인증'
                .anyRequest().authenticated()
                .and()
                // [로그인 기능]
                .formLogin()
                // 로그인 View 제공 (GET /user/login)
                .loginPage("/login")
                // 로그인 처리 (POST /user/login)
                .loginProcessingUrl("/login")
                // 로그인 처리 후 성공 시 URL
                .defaultSuccessUrl("/")
                // 로그인 처리 후 실패 시 URL
                .failureUrl("/login?error")
                .permitAll()
                .and()
                // [로그아웃 기능]
                .logout()
                // 로그아웃 처리 URL
                .logoutUrl("/logout")
                .permitAll();
    }
}
