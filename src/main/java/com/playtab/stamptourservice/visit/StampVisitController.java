package com.playtab.stamptourservice.visit;

import com.playtab.stamptourservice.common.response.ApiResponse;
import com.playtab.stamptourservice.visit.dto.ProgressResponse;
import com.playtab.stamptourservice.visit.dto.StampVisitCreateResponse;
import com.playtab.stamptourservice.visit.dto.StampVisitListResponse;
import com.playtab.stamptourservice.visit.dto.StampVisitRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 방문 기록 관련 API 컨트롤러
@RestController
@RequiredArgsConstructor
public class StampVisitController {

    private final StampVisitService stampVisitService;

    // 임시 userId 사용
    @GetMapping("/api/v1/visits/me")
    public ApiResponse<StampVisitListResponse> getMyVisits() {
        Long userId = 1L;
        return ApiResponse.success(stampVisitService.getMyVisits(userId));
    }

    // 임시 userId 사용
    @GetMapping("/api/v1/visits/me/progress")
    public ApiResponse<ProgressResponse> getMyProgress() {
        Long userId = 1L;
        return ApiResponse.success(stampVisitService.getProgress(userId));
    }

    // 임시 userId 사용
    @PostMapping("/api/v1/visits")
    public ApiResponse<StampVisitCreateResponse> createVisit(@RequestBody StampVisitRequest request) {
        Long userId = 1L;
        return ApiResponse.success(stampVisitService.createVisit(userId, request.getSpotId()));
    }
}