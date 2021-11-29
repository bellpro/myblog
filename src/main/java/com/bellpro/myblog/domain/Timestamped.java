package com.bellpro.myblog.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter // get 메소드 자동 생성
@MappedSuperclass   // 상속 받은 클래스가 생성날짜/수정날짜 컬럼으로 사용하도록 설정
@EntityListeners(AuditingEntityListener.class)  // 테이블에 날짜 자동 기록
public abstract class Timestamped { // 추상화 클래스 설정
    @CreatedDate                // 생성날짜 설정
    private String createdAt;   // 생성날짜

    @LastModifiedDate           // 수정날짜 설정
    private String modifiedAt;  // 수정날짜

    @PrePersist
    public void onPrePersist(){
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        this.modifiedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }

}
