package com.runningapp.StravaTest.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StravaTokenResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Long expiresAt;
}
