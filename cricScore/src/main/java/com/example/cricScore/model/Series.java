package com.example.cricScore.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seriesName;
    private String createdBy; // username

    private String team1Name;
    private String team2Name;
    private int playersPerTeam;
    private int overs;

    @ElementCollection
    private List<String> team1Players;

    @ElementCollection
    private List<String> team2Players;
}
