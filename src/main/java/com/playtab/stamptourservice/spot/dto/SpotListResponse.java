package com.playtab.stamptourservice.spot.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

// 스팟 목록 응답 DTO
@Getter
public class SpotListResponse {

    private final int totalCount;
    private final int visitedCount;
    private final List<SpotResponse> spots;

    @Builder
    public SpotListResponse(int totalCount, int visitedCount, List<SpotResponse> spots) {
        this.totalCount = totalCount;
        this.visitedCount = visitedCount;
        this.spots = spots;
    }
}