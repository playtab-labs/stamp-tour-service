package com.playtab.stamptourservice.spot.dto;

import com.playtab.stamptourservice.spot.Spot;
import lombok.Builder;
import lombok.Getter;

// 개별 스팟 응답 DTO
@Getter
public class SpotResponse {

    private final Long spotId;
    private final String name;
    private final String description;
    private final Integer displayOrder;
    private final Boolean isActive;

    @Builder
    public SpotResponse(Long spotId, String name, String description, Integer displayOrder, Boolean isActive) {
        this.spotId = spotId;
        this.name = name;
        this.description = description;
        this.displayOrder = displayOrder;
        this.isActive = isActive;
    }

    // Spot 엔티티를 SpotResponse DTO로 변환
    public static SpotResponse from(Spot spot) {
        return SpotResponse.builder()
                .spotId(spot.getId())
                .name(spot.getName())
                .description(spot.getDescription())
                .displayOrder(spot.getDisplayOrder())
                .isActive(spot.getIsActive())
                .build();
    }
}