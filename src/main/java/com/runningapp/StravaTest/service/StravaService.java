package com.runningapp.StravaTest.service;

import com.runningapp.StravaTest.entity.Activities;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StravaService {

    ResponseEntity<List> getUserActivities(String accessToken);
    ResponseEntity<Object> getUserDetails(String accessToken);
}
