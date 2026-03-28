package com.playtab.stamptourservice.visit.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

// 스탬프 적립 응답 DTO
@Getter
public class StampVisitCreateResponse {

    private final Long spotId;
    private final String spotName;
    private final LocalDateTime visitedAt;
    private final long visitedCount;
    private final int totalCount;
    private final boolean completed;

    @Builder
    public StampVisitCreateResponse(Long spotId, String spotName, LocalDateTime visitedAt,
                                    long visitedCount, int totalCount, boolean completed) {
        this.spotId = spotId;
        this.spotName = spotName;
        this.visitedAt = visitedAt;
        this.visitedCount = visitedCount;
        this.totalCount = totalCount;
        this.completed = completed;
    }
}