package com.playtab.stamptourservice.spot.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

// 스팟 목록 응답 DTO
@Getter
public class SpotListResponse {

    // 전체 스팟 개수
    private final int totalCount;

    // 스팟 목록
    private final List<SpotResponse> spots;

    @Builder
    public SpotListResponse(int totalCount, List<SpotResponse> spots) {
        this.totalCount = totalCount;
        this.spots = spots;
    }
}