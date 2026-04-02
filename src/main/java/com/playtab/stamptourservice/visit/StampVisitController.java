package com.playtab.stamptourservice.visit;

import com.playtab.stamptourservice.common.response.ApiResponse;
import com.playtab.stamptourservice.visit.dto.ProgressResponse;
import com.playtab.stamptourservice.visit.dto.StampVisitCreateResponse;
import com.playtab.stamptourservice.visit.dto.StampVisitListResponse;
import com.playtab.stamptourservice.visit.dto.StampVisitRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "StampVisit", description = "방문 기록 관련 API")
@RestController
@RequiredArgsConstructor
public class StampVisitController {

    private final StampVisitService stampVisitService;

    @Operation(summary = "내 방문 기록 조회")
    @GetMapping("/api/v1/visits/me")
    public ApiResponse<StampVisitListResponse> getMyVisits() {
        String userId = "temp-user-id"; // 임시 userId
        return ApiResponse.success(stampVisitService.getMyVisits(userId));
    }

    @Operation(summary = "내 진행률 조회")
    @GetMapping("/api/v1/visits/me/progress")
    public ApiResponse<ProgressResponse> getMyProgress() {
        String userId = "temp-user-id"; // 임시 userId
        return ApiResponse.success(stampVisitService.getProgress(userId));
    }

    @Operation(summary = "방문 기록 생성")
    @PostMapping("/api/v1/visits")
    public ApiResponse<StampVisitCreateResponse> createVisit(@RequestBody StampVisitRequest request) {
        String userId = "temp-user-id"; // 임시 userId
        return ApiResponse.success(stampVisitService.createVisit(userId, request.getSpotId()));
    }
}