package com.playtab.stamptourservice.visit;

import com.playtab.stamptourservice.common.exception.AlreadyVisitedException;
import com.playtab.stamptourservice.common.exception.SpotNotFoundException;
import com.playtab.stamptourservice.spot.Spot;
import com.playtab.stamptourservice.spot.SpotRepository;
import com.playtab.stamptourservice.visit.dto.ProgressResponse;
import com.playtab.stamptourservice.visit.dto.StampVisitCreateResponse;
import com.playtab.stamptourservice.visit.dto.StampVisitListResponse;
import com.playtab.stamptourservice.visit.dto.StampVisitResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// 방문 기록 관련 비즈니스 로직 처리
@Service
@RequiredArgsConstructor
public class StampVisitService {

    private final StampVisitRepository stampVisitRepository;
    private final SpotRepository spotRepository;

    // 특정 유저가 특정 스팟을 방문했는지 확인
    public boolean hasVisited(Long userId, Spot spot) {
        return stampVisitRepository.existsByUserIdAndSpot(userId, spot);
    }

    // 특정 유저가 방문한 총 스팟 수 조회
    public long countVisitedSpots(Long userId) {
        return stampVisitRepository.countByUserId(userId);
    }

    // 특정 유저의 방문 기록 목록 조회
    public StampVisitListResponse getMyVisits(Long userId) {
        List<StampVisitResponse> visitResponses = stampVisitRepository.findAllByUserId(userId)
                .stream()
                .map(visit -> StampVisitResponse.builder()
                        .spotId(visit.getSpot().getId())
                        .spotName(visit.getSpot().getName())
                        .visitedAt(visit.getCreatedAt())
                        .build())
                .toList();

        return StampVisitListResponse.builder()
                .totalCount(visitResponses.size())
                .visits(visitResponses)
                .build();
    }

    // 특정 유저의 진행률 조회
    public ProgressResponse getProgress(Long userId) {
        long visitedCount = stampVisitRepository.countByUserId(userId);
        int totalCount = spotRepository.findAll().size();

        return ProgressResponse.builder()
                .visitedCount(visitedCount)
                .totalCount(totalCount)
                .completed(totalCount > 0 && visitedCount == totalCount)
                .build();
    }

    // 스탬프 적립
    public StampVisitCreateResponse createVisit(Long userId, Long spotId) {
        Spot spot = spotRepository.findById(spotId)
                .orElseThrow(() -> new SpotNotFoundException("존재하지 않는 스팟입니다."));

        if (stampVisitRepository.existsByUserIdAndSpot(userId, spot)) {
            throw new AlreadyVisitedException("이미 방문한 스팟입니다.");
        }

        StampVisit stampVisit = StampVisit.builder()
                .userId(userId)
                .spot(spot)
                .createdAt(LocalDateTime.now())
                .build();

        stampVisitRepository.save(stampVisit);

        long visitedCount = stampVisitRepository.countByUserId(userId);
        int totalCount = spotRepository.findAll().size();

        return StampVisitCreateResponse.builder()
                .spotId(spot.getId())
                .spotName(spot.getName())
                .visitedAt(stampVisit.getCreatedAt())
                .visitedCount(visitedCount)
                .totalCount(totalCount)
                .completed(totalCount > 0 && visitedCount == totalCount)
                .build();
    }
}