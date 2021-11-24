package com.bellpro.myblog.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter // get 메소드 자동 생성
@MappedSuperclass   // 상속 받은 클래스는 멤버변수(생성날짜, 수정날짜) 사용 가능
@EntityListeners(AuditingEntityListener.class)  // 생성/수정 되었을 때 자동으로 기록
public abstract class Timestamped { // 추상화
    @CreatedDate        // 생성날짜
    private LocalDateTime createdAt;

    @LastModifiedDate   // 수정날짜
    private LocalDateTime modifiedAt;
}
