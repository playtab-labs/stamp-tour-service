package com.playtab.stamptourservice.spot;

import com.playtab.stamptourservice.spot.dto.SpotListResponse;
import com.playtab.stamptourservice.spot.dto.SpotResponse;
import com.playtab.stamptourservice.visit.StampVisit;
import com.playtab.stamptourservice.visit.StampVisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;
    private final StampVisitRepository stampVisitRepository;

    // Jira 조회 방향 기준: 전체 spot + 내 방문 여부
    public SpotListResponse getMyStampSpots(String userId) {
        List<Spot> spots = spotRepository.findAll();

        List<SpotResponse> spotResponses = spots.stream()
                .map(spot -> {
                    Optional<StampVisit> optionalVisit = stampVisitRepository.findByUserIdAndSpot(userId, spot);

                    Location location = spot.getLocation();

                    return SpotResponse.builder()
                            .spotId(spot.getId())
                            .spotName(spot.getName())
                            .spotDescription(spot.getDescription())
                            .visited(optionalVisit.isPresent())
                            .visitedAt(optionalVisit.map(StampVisit::getCreatedAt).orElse(null))
                            .latitude(location != null ? location.getLatitude() : null)
                            .longitude(location != null ? location.getLongitude() : null)
                            .build();
                })
                .toList();

        int totalCount = spotResponses.size();
        int visitedCount = (int) spotResponses.stream()
                .filter(SpotResponse::getVisited)
                .count();

        return SpotListResponse.builder()
                .totalCount(totalCount)
                .visitedCount(visitedCount)
                .spots(spotResponses)
                .build();
    }
}