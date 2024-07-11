package com.runningapp.StravaTest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.runningapp.StravaTest.entity.UserDetails;
import com.runningapp.StravaTest.repository.UserDetailsRepository;
import com.runningapp.StravaTest.service.StravaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;

@Controller
@RequestMapping("/strava-service")
public class StravaController {

    @Autowired
    private StravaService stravaService;
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;


    @GetMapping("/home")
    public String getUserDetails(Model model, OAuth2AuthenticationToken authenticationToken) throws JsonProcessingException {
        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
                authenticationToken.getAuthorizedClientRegistrationId(), authenticationToken.getName()
        );
        Object userDetail = stravaService.getUserDetails(authorizedClient.getAccessToken().getTokenValue()).getBody();
        assert userDetail != null;
        UserDetails userDetails = createUserDetailsObject(userDetail);
        userDetailsRepository.save(userDetails);
        model.addAttribute("accessToken", authorizedClient.getAccessToken().getTokenValue());
        model.addAttribute("UserDetails", userDetail.toString());
        return "home";
    }

    private UserDetails createUserDetailsObject(Object userDetail) {
        UserDetails userDetails = new UserDetails();
        Integer athleteId = (Integer) ((LinkedHashMap<?, ?>) userDetail).get("id");
        String username = (String) ((LinkedHashMap<?, ?>) userDetail).get("username");
        userDetails.setUsername(username);
        userDetails.setAthleteId(athleteId);
        return userDetails;
    }

    @GetMapping("/")
    public String displayIndexPage(Model model){
        return "index";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String testController(){
        return "Hello from strava service";
    }

}
