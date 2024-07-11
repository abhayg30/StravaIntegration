package com.runningapp.StravaTest.details;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrations;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

@Getter
@Setter
public class StravaAppDetails {

    private static String clientId = "128948";

    private static String redirectUri = "{baseUrl}/login/oauth2/code/my-running";

    private static String scope = "activity:read_all,profile:read_all,read_all";

    private static String clientSecret = "0b2c7e227dac5ca1dd7139c95caebf98f7cec84f";

    private static String authorizationURI = "https://www.strava.com/oauth/authorize";

    private static String tokenURI = "https://www.strava.com/oauth/token";

    private static String userInfoURI = "https://www.strava.com/api/v3/athlete";

    public static ClientRegistration getClientRegistration(){
        return ClientRegistration.withRegistrationId("strava")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .authorizationUri(authorizationURI)
                .tokenUri(tokenURI)
                .scope(scope)
                .redirectUri(redirectUri)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .userInfoUri(userInfoURI)
                .userNameAttributeName("id")
                .build();
    }

}
