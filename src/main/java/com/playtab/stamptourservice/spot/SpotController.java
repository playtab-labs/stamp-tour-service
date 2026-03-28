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

    // final 필드 생성자 주입
    private final SpotService spotService;

    // 활성 스팟 목록 조회 API
    @GetMapping("/api/v1/spots")
    public ApiResponse<SpotListResponse> getSpots() {
        return ApiResponse.success(spotService.getActiveSpotResponses());
    }
}