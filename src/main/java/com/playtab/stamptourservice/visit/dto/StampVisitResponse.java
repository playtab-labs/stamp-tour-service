package com.playtab.stamptourservice.visit.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

// 개별 방문 기록 응답 DTO
@Getter
public class StampVisitResponse {

    private final Long spotId;
    private final String spotName;
    private final LocalDateTime visitedAt;

    @Builder
    public StampVisitResponse(Long spotId, String spotName, LocalDateTime visitedAt) {
        this.spotId = spotId;
        this.spotName = spotName;
        this.visitedAt = visitedAt;
    }
}