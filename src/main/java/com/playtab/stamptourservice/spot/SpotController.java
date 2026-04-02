package com.playtab.stamptourservice.spot;

import com.playtab.stamptourservice.common.response.ApiResponse;
import com.playtab.stamptourservice.spot.dto.SpotListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Spot", description = "스팟 관련 API")
@RestController
@RequiredArgsConstructor
public class SpotController {

    private final SpotService spotService;

    @Operation(summary = "내 스탬프 스팟 조회")
    @GetMapping("/api/v1/spots")
    public ApiResponse<SpotListResponse> getMyStampSpots() {
        Long userId = 1L; // 임시 userId
        return ApiResponse.success(spotService.getMyStampSpots(userId));
    }
}