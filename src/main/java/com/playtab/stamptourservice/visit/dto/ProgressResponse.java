package com.playtab.stamptourservice.visit.dto;

import lombok.Builder;
import lombok.Getter;

// 진행률 응답 DTO
@Getter
public class ProgressResponse {

    private final long visitedCount;
    private final int totalCount;
    private final boolean completed;

    @Builder
    public ProgressResponse(long visitedCount, int totalCount, boolean completed) {
        this.visitedCount = visitedCount;
        this.totalCount = totalCount;
        this.completed = completed;
    }
}