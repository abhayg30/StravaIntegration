package com.runningapp.StravaTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StravaTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(StravaTestApplication.class, args);
	}

}
