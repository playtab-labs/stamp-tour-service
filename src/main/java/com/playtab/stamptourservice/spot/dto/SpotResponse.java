package com.playtab.stamptourservice.spot.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

// 개별 스팟 조회 응답 DTO
@Getter
public class SpotResponse {

    private final Long spotId;
    private final String spotName;
    private final String spotDescription;
    private final Boolean visited;
    private final LocalDateTime visitedAt;

    @Builder
    public SpotResponse(Long spotId, String spotName, String spotDescription,
                        Boolean visited, LocalDateTime visitedAt) {
        this.spotId = spotId;
        this.spotName = spotName;
        this.spotDescription = spotDescription;
        this.visited = visited;
        this.visitedAt = visitedAt;
    }
}