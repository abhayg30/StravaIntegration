package com.runningapp.StravaTest.repository;

import com.runningapp.StravaTest.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

}
