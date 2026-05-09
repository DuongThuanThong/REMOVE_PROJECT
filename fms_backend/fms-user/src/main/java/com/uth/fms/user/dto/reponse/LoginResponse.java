package com.uth.fms.user.dto.reponse;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder(toBuilder = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class LoginResponse {
    String accessToken;
    String refreshToken;
    @Builder.Default
    String tokenType = "Bearer";
    String username;
    String fullName;
    String roleS;
}