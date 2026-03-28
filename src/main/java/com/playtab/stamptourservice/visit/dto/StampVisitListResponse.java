package com.playtab.stamptourservice.visit.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

// 방문 기록 목록 응답 DTO
@Getter
public class StampVisitListResponse {

    private final int totalCount;
    private final List<StampVisitResponse> visits;

    @Builder
    public StampVisitListResponse(int totalCount, List<StampVisitResponse> visits) {
        this.totalCount = totalCount;
        this.visits = visits;
    }
}