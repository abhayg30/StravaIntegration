package com.runningapp.StravaTest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDetails {


    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;
    @Column(nullable = false, unique = true)
    private Integer athleteId;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "athlete_id", referencedColumnName = "athleteId")
    List<Activities> activitiesList;

}


