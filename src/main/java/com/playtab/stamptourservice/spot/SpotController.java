package com.playtab.stamptourservice.spot;

import com.playtab.stamptourservice.common.response.ApiResponse;
import com.playtab.stamptourservice.spot.dto.SpotListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 스팟 관련 API 컨트롤러
@RestController
@RequiredArgsConstructor
public class SpotController {

    private final SpotService spotService;

    // 내 스탬프 스팟 조회 API
    @GetMapping("/api/v1/spots")
    public ApiResponse<SpotListResponse> getMyStampSpots() {
        Long userId = 1L; // 임시 userId
        return ApiResponse.success(spotService.getMyStampSpots(userId));
    }
}