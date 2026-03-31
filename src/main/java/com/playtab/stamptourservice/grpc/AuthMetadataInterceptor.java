package com.playtab.stamptourservice.grpc;

import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.springframework.stereotype.Component;

@Component
@GrpcGlobalServerInterceptor
public class AuthMetadataInterceptor implements ServerInterceptor {

    public static final Context.Key<String> USER_ID_CTX_KEY = Context.key("userId");

    private static final Metadata.Key<String> USER_ID_METADATA_KEY =
            Metadata.Key.of("user-id", Metadata.ASCII_STRING_MARSHALLER);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call,
            Metadata headers,
            ServerCallHandler<ReqT, RespT> next) {

        String userId = headers.get(USER_ID_METADATA_KEY);

        Context context = Context.current()
                .withValue(USER_ID_CTX_KEY, userId);

        return Contexts.interceptCall(context, call, headers, next);
    }
}