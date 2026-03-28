package com.playtab.stamptourservice.visit;

import com.playtab.stamptourservice.spot.Spot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 방문 기록 관련 비즈니스 로직 처리
@Service
@RequiredArgsConstructor
public class StampVisitService {

    // final 필드 생성자 주입
    private final StampVisitRepository stampVisitRepository;

    // 특정 유저가 특정 스팟을 방문했는지 확인
    public boolean hasVisited(Long userId, Spot spot) {
        return stampVisitRepository.existsByUserIdAndSpot(userId, spot);
    }

    // 특정 유저가 방문한 총 스팟 수 조회
    public long countVisitedSpots(Long userId) {
        return stampVisitRepository.countByUserId(userId);
    }
}