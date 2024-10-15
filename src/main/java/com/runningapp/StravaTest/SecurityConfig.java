package com.runningapp.StravaTest;

import com.runningapp.StravaTest.details.StravaAppDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    @Order(1)
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        return http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
//                .oauth2Login(Customizer.withDefaults())
//                .oauth2ResourceServer(rsc ->rsc.jwt(Customizer.withDefaults())).build();
//    }

    @Bean
    public SecurityFilterChain stravaFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authz -> authz
                        .requestMatchers(new AntPathRequestMatcher("/strava-service/"))
                        .permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(Customizer.withDefaults())
                .oauth2ResourceServer(rsc -> rsc.jwt(Customizer.withDefaults()))
                .logout(logout -> logout
                        .logoutSuccessHandler(oidcLogoutSuccessHandler())
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID"));
        return http.build();
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {

        return new InMemoryOAuth2AuthorizedClientService(
                getClientRegistrationRepository());
    }
    @Bean
    public ClientRegistrationRepository getClientRegistrationRepository(){
        return new InMemoryClientRegistrationRepository(
                StravaAppDetails.getClientRegistration()
        );
    }


    private OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(
                getClientRegistrationRepository());
        successHandler.setPostLogoutRedirectUri("http://localhost:8090/");
        return successHandler;
    }
}