package com.playtab.stamptourservice.spot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Spot 엔티티용 JPA Repository
public interface SpotRepository extends JpaRepository<Spot, Long> {

    // 활성화된 스팟만 displayOrder 오름차순으로 조회
    List<Spot> findAllByIsActiveTrueOrderByDisplayOrderAsc();
}