package com.example.cricScore.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long seriesId;

    private String teamA;
    private String teamB;

    private int scoreA = 0;
    private int scoreB = 0;

    private int wicketsA = 0;
    private int wicketsB = 0;
    private int currentOver = 0;
private int currentBall = 0;
private int bouncerCount = 0;

    private int overs; // total overs for match

    private String tossWinner;
    private String choice; // Bat or Field

    private String currentBattingTeam;
    private String striker;
    private String nonStriker;
    private String currentBowler;

    private int ballsBowledInCurrentOver = 0;
    private int bouncersBowledInCurrentOver = 0;
    private boolean freeHitNextBall = false;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BallSummary> ballSummaries = new ArrayList<>();

    // Constructors
    public Match() {}

    public Match(String teamA, String teamB, int overs) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.overs = overs;
    }

    // 🔥 Helper Methods 🔥

    // Rotate strike between striker and non-striker
    public void rotateStrike() {
        String temp = striker;
        striker = nonStriker;
        nonStriker = temp;
    }

    // After over ends, select new bowler (for now random or user input later)
    public void changeBowler() {
        this.currentBowler = "New Bowler"; // Placeholder; In real app, ask user to pick next bowler
    }

}
