package com.playtab.stamptourservice.common.auth;

import com.playtab.stamptourservice.grpc.AuthMetadataInterceptor;

public final class AuthContext {

    private AuthContext() {}

    public static String getUserId() {
        String userId = AuthMetadataInterceptor.USER_ID_CTX_KEY.get();

        if (userId == null || userId.isBlank()) {
            throw new IllegalStateException("Missing user-id in metadata");
        }

        return userId;
    }
}