
package com.example.cricScore.repository;

import com.example.cricScore.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findBySeriesId(Long seriesId);
}
