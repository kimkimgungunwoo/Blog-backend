package com.example.blogex.common.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
//직접 테이블로 만들지 않고, 상속받는 테이블에 필드값 주입만 함
@MappedSuperclass
//JPA 이벤트 감지해서 자동으로 값 바꿔줌
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime CreateAt;

    @LastModifiedDate
    private LocalDateTime UpdateAt;

}
