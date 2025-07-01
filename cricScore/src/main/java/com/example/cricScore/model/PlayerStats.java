package com.example.cricScore.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PlayerStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long matchId;

    private String playerName;
    private String teamName;

    private int runsScored;
    private int ballsFaced;

    private int wicketsTaken;
    private int oversBowled;
    private int runsGiven;
}
