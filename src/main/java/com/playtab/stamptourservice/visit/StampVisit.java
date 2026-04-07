package com.playtab.stamptourservice.visit;

import com.playtab.stamptourservice.spot.Spot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// JPA 엔티티: 유저의 스탬프 방문 기록 테이블과 매핑
@Entity
@Table(
        name = "stamp_visit",
        uniqueConstraints = {
                // 같은 유저가 같은 스팟을 중복 방문하지 못하도록 제약
                @UniqueConstraint(
                        name = "uk_user_spot",
                        columnNames = {"user_id", "spot_id"}
                )
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StampVisit {

    // 방문 기록 PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 방문한 유저 ID (UUID)
    @Column(name = "user_id", nullable = false)
    private String userId;

    // 방문한 스팟 (N:1 관계)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", nullable = false)
    private Spot spot;

    // 방문 시각
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // 빌더 생성자
    @Builder
    public StampVisit(String userId, Spot spot, LocalDateTime createdAt) {
        this.userId = userId;
        this.spot = spot;
        this.createdAt = createdAt;
    }
}