package com.playtab.stamptourservice.spot.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SpotResponse {

    private final Long spotId;
    private final String spotName;
    private final String spotDescription;
    private final Boolean visited;
    private final LocalDateTime visitedAt;
    private final Double latitude;
    private final Double longitude;

    @Builder
    public SpotResponse(Long spotId, String spotName, String spotDescription,
                        Boolean visited, LocalDateTime visitedAt, Double latitude, Double longitude) {
        this.spotId = spotId;
        this.spotName = spotName;
        this.spotDescription = spotDescription;
        this.visited = visited;
        this.visitedAt = visitedAt;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}