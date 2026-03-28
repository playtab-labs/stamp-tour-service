package com.playtab.stamptourservice.visit;

import com.playtab.stamptourservice.spot.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// StampVisit 엔티티용 JPA Repository
public interface StampVisitRepository extends JpaRepository<StampVisit, Long> {

    // 특정 유저가 특정 스팟을 이미 방문했는지 확인
    boolean existsByUserIdAndSpot(Long userId, Spot spot);

    // 특정 유저의 전체 방문 개수 조회
    long countByUserId(Long userId);

    // 특정 유저의 전체 방문 기록 조회
    List<StampVisit> findAllByUserId(Long userId);

    // 특정 유저의 특정 스팟 방문 기록 1건 조회
    Optional<StampVisit> findByUserIdAndSpot(Long userId, Spot spot);
}