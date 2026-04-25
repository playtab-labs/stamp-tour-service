package com.playtab.stamptourservice.visit;

import com.playtab.stamptourservice.common.exception.AlreadyVisitedException;
import com.playtab.stamptourservice.common.exception.SpotNotFoundException;
import com.playtab.stamptourservice.common.exception.TooFarFromSpotException;
import com.playtab.stamptourservice.spot.Location;
import com.playtab.stamptourservice.spot.Spot;
import com.playtab.stamptourservice.spot.SpotRepository;
import com.playtab.stamptourservice.visit.dto.ProgressResponse;
import com.playtab.stamptourservice.visit.dto.StampVisitCreateResponse;
import com.playtab.stamptourservice.visit.dto.StampVisitListResponse;
import com.playtab.stamptourservice.visit.dto.StampVisitResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StampVisitService {

    private final StampVisitRepository stampVisitRepository;
    private final SpotRepository spotRepository;

    // 특정 유저가 특정 스팟을 방문했는지 확인
    public boolean hasVisited(String userId, Spot spot) {
        return stampVisitRepository.existsByUserIdAndSpot(userId, spot);
    }

    // 특정 유저가 방문한 총 스팟 수 조회
    public long countVisitedSpots(String userId) {
        return stampVisitRepository.countByUserId(userId);
    }

    // 특정 유저의 방문 기록 목록 조회
    public StampVisitListResponse getMyVisits(String userId) {
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
    public ProgressResponse getProgress(String userId) {
        long visitedCount = stampVisitRepository.countByUserId(userId);
        int totalCount = spotRepository.findAll().size();

        return ProgressResponse.builder()
                .visitedCount(visitedCount)
                .totalCount(totalCount)
                .completed(totalCount > 0 && visitedCount == totalCount)
                .build();
    }

    // 적정 거리 확정 후 수치 수정 필요
    @Value("${stamp-tour.max-distance-meters:100.0}")
    private double maxDistanceMeters;

    private static final double EARTH_RADIUS_METERS = 6_371_000.0;

    private double calculateDistance(double lat1, double lng1, double lat2, double lng2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        return EARTH_RADIUS_METERS * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }

    @Transactional
    public StampVisitCreateResponse createVisit(String userId, Long spotId, double userLat, double userLng) {
        Spot spot = spotRepository.findById(spotId)
                .orElseThrow(() -> new SpotNotFoundException("Spot not found."));

        Location location = spot.getLocation();
        if (location != null) {
            double distance = calculateDistance(userLat, userLng, location.getLatitude(), location.getLongitude());
            if (distance > maxDistanceMeters) {
                throw new TooFarFromSpotException("Too far from the spot.");
            }
        }

        if (stampVisitRepository.existsByUserIdAndSpot(userId, spot)) {
            throw new AlreadyVisitedException("Already visited this spot.");
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