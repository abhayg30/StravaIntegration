package com.runningapp.StravaTest.service;

import com.runningapp.StravaTest.entity.Activities;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StravaServiceImpl implements StravaService{
    @Override
    public ResponseEntity<List> getUserActivities(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = createHttpHeaders(accessToken);
        String userInfoEndpointUri = "https://www.strava.com/api/v3/athlete/activities";
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
        ResponseEntity<List> response = restTemplate.exchange(userInfoEndpointUri, HttpMethod.GET, entity,
                List.class);

        return response;
    }

    @Override
    public ResponseEntity<Object> getUserDetails(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = createHttpHeaders(accessToken);
        String userInfoEndpointUri = "https://www.strava.com/api/v3/athlete";
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Object> response = restTemplate.exchange(userInfoEndpointUri, HttpMethod.GET, entity,
                Object.class);
        return response;
    }

    private HttpHeaders createHttpHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer "+accessToken);
        return headers;

    }
}
