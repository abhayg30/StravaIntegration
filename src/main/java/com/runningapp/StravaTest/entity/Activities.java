package com.runningapp.StravaTest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "activities")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Activities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID messageID;
    @Column
    String name;
    @Column
    String userName;
    @Column
    Long timeStamp;
    @Column
    boolean isSent;
}
