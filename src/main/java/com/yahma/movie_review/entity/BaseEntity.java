package com.yahma.movie_review.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class }) // JPA 내부에서 엔티티 객체가 생성/변경되는 것을 감지하는 역할
@Getter
abstract class BaseEntity {
    @CreatedDate
    @Column(name = "regdate", updatable = false) // updatable: DB에 반영할 때 컬럼 값이 변경되지 않게 함
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;
}
