package com.playtab.stamptourservice.spot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// JPA 엔티티: 스탬프 스팟 테이블과 매핑
@Entity
@Table(name = "spot")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Spot {

    // 스팟 PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 스팟 이름
    @Column(nullable = false, length = 100)
    private String name;

    // 스팟 설명
    @Column(columnDefinition = "TEXT")
    private String description;

    // 생성 시각
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // 수정 시각
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public Spot(String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}