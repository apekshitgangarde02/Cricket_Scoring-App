package com.example.cricScore.controller;

import com.example.cricScore.model.BallSummary;
import com.example.cricScore.model.Match;
import com.example.cricScore.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    // Create a new match with players and initial details
    @PostMapping
    public Match createMatch(@RequestBody Match match) {
        return matchRepository.save(match);
    }

    // Get match details by ID
    @GetMapping("/{id}")
    public Match getMatchById(@PathVariable Long id) {
        return matchRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Match not found"));
    }

    // Toss and Bat/Field choice
    @PutMapping("/{id}/toss")
    public Match setToss(@PathVariable Long id, @RequestParam String tossWinner, @RequestParam String choice) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found"));
        
        match.setTossWinner(tossWinner);
        match.setChoice(choice);
        return matchRepository.save(match);
    }

    // Update score and ball-by-ball events
    @PutMapping("/{id}/ball-summary")
    public Match addBallSummary(@PathVariable Long id, @RequestBody BallSummary ballSummary) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found"));

        // Add ball to the match record
        match.getBallSummaries().add(ballSummary);

        // Bouncer logic
        if (ballSummary.isBouncer()) {
            match.setBouncerCount(match.getBouncerCount() + 1);

            if (match.getBouncerCount() > 2) {
                ballSummary.setNoBall(true); // Automatically make it a no ball
                ballSummary.setFreeHit(true);
            }
        }

        // Wide ball handling
        if (ballSummary.isWide()) {
            match.setScoreA(match.getScoreA() + 1);
        }
        // No ball handling
        else if (ballSummary.isNoBall()) {
            match.setScoreA(match.getScoreA() + 1);
        }
        // Normal ball
        else {
            match.setCurrentBall(match.getCurrentBall() + 1); // Only increment ball on legal delivery

            match.setScoreA(match.getScoreA() + ballSummary.getRuns());

            // Strike change if odd runs
            if (ballSummary.getRuns() % 2 != 0) {
                swapStriker(match);
            }
        }

        // Wicket handling
        if ("Wicket".equalsIgnoreCase(ballSummary.getResult())) {
            if (match.getWicketsA() < 10) {
                match.setWicketsA(match.getWicketsA() + 1);
            }
        }

        // End of over
        if (match.getCurrentBall() == 6) {
            match.setCurrentOver(match.getCurrentOver() + 1);
            match.setCurrentBall(0);
            match.setBouncerCount(0); // Reset bouncer count for new over
            swapStriker(match); // Change striker at end of over
        }

        return matchRepository.save(match);
    }

    private void swapStriker(Match match) {
        String temp = match.getStriker();
        match.setStriker(match.getNonStriker());
        match.setNonStriker(temp);
    }

    // Get all matches
    @GetMapping
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }
}
