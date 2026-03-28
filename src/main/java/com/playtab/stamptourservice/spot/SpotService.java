package com.playtab.stamptourservice.spot;

import com.playtab.stamptourservice.spot.dto.SpotListResponse;
import com.playtab.stamptourservice.spot.dto.SpotResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// 스팟 관련 비즈니스 로직 처리
@Service
@RequiredArgsConstructor
public class SpotService {

    // final 필드 생성자 주입 (Lombok)
    private final SpotRepository spotRepository;

    // 활성화된 Spot 엔티티 목록 조회
    public List<Spot> getActiveSpots() {
        return spotRepository.findAllByIsActiveTrueOrderByDisplayOrderAsc();
    }

    // 활성화된 Spot 목록을 응답 DTO 형태로 변환해서 반환
    public SpotListResponse getActiveSpotResponses() {
        List<SpotResponse> spotResponses = spotRepository.findAllByIsActiveTrueOrderByDisplayOrderAsc()
                .stream()
                .map(SpotResponse::from)
                .toList();

        return SpotListResponse.builder()
                .totalCount(spotResponses.size())
                .spots(spotResponses)
                .build();
    }
}