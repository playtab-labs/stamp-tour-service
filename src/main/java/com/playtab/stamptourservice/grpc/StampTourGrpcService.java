package com.playtab.stamptourservice.grpc;

import com.playtab.stamptourservice.common.auth.AuthContext;
import com.playtab.stamptourservice.spot.SpotService;
import com.playtab.stamptourservice.spot.dto.SpotListResponse;
import com.playtab.stamptourservice.visit.StampVisitService;
import com.playtab.stamptourservice.visit.dto.StampVisitCreateResponse;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

@GrpcService
@RequiredArgsConstructor
public class StampTourGrpcService extends StampTourServiceGrpc.StampTourServiceImplBase {

    private final SpotService spotService;
    private final StampVisitService stampVisitService;

    @Override
    public void visit(VisitRequest request, StreamObserver<VisitResponse> responseObserver) {
        String userId = AuthContext.getUserId();

        try {
            StampVisitCreateResponse result = stampVisitService.createVisit(userId, request.getSpotId());

            VisitResponse response = VisitResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage(result.getSpotName() + " 스팟 방문이 완료되었습니다.")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            VisitResponse response = VisitResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage(e.getMessage())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getMyStamps(GetMyStampsRequest request, StreamObserver<GetMyStampsResponse> responseObserver) {
        String userId = AuthContext.getUserId();

        SpotListResponse result = spotService.getMyStampSpots(userId);

        GetMyStampsResponse.Builder responseBuilder = GetMyStampsResponse.newBuilder()
                .setTotalCount(result.getTotalCount())
                .setVisitedCount(result.getVisitedCount());

        result.getSpots().forEach(spot -> {
            StampSpot grpcSpot = StampSpot.newBuilder()
                    .setSpotId(spot.getSpotId())
                    .setSpotName(spot.getSpotName())
                    .setSpotDescription(spot.getSpotDescription() == null ? "" : spot.getSpotDescription())
                    .setVisited(Boolean.TRUE.equals(spot.getVisited()))
                    .setVisitedAt(spot.getVisitedAt() == null ? "" : spot.getVisitedAt().toString())
                    .setLatitude(spot.getLatitude() != null ? spot.getLatitude() : 0.0)
                    .setLongitude(spot.getLongitude() != null ? spot.getLongitude() : 0.0)
                    .build();

            responseBuilder.addSpots(grpcSpot);
        });

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
}