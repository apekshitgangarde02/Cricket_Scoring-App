package com.example.cricScore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ball_summary")
public class BallSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int runs;            // runs scored on this ball
    private boolean wide;        // wide ball
    private boolean noBall;      // no ball
    private boolean freeHit;     // is this ball a free hit
    private boolean bouncer;     // was this ball a bouncer
    private String result;       // "Run", "Wicket", "Boundary", "Six", "Wide", "NoBall", etc.

    private String strikerName;  // name of batsman who played this ball
    private String bowlerName;   // name of bowler

    // Constructors
    public BallSummary() {}

    public BallSummary(int runs, boolean wide, boolean noBall, boolean freeHit, boolean bouncer, String result, String strikerName, String bowlerName) {
        this.runs = runs;
        this.wide = wide;
        this.noBall = noBall;
        this.freeHit = freeHit;
        this.bouncer = bouncer;
        this.result = result;
        this.strikerName = strikerName;
        this.bowlerName = bowlerName;
    }

    // Getters and Setters

    public Long getId() { return id; }

    public int getRuns() { return runs; }
    public void setRuns(int runs) { this.runs = runs; }

    public boolean isWide() { return wide; }
    public void setWide(boolean wide) { this.wide = wide; }

    public boolean isNoBall() { return noBall; }
    public void setNoBall(boolean noBall) { this.noBall = noBall; }

    public boolean isFreeHit() { return freeHit; }
    public void setFreeHit(boolean freeHit) { this.freeHit = freeHit; }

    public boolean isBouncer() { return bouncer; }
    public void setBouncer(boolean bouncer) { this.bouncer = bouncer; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public String getStrikerName() { return strikerName; }
    public void setStrikerName(String strikerName) { this.strikerName = strikerName; }

    public String getBowlerName() { return bowlerName; }
    public void setBowlerName(String bowlerName) { this.bowlerName = bowlerName; }
}
