package com.example.cricScore.repository;

import com.example.cricScore.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeriesRepository extends JpaRepository<Series, Long> {
    List<Series> findAllByOrderByIdDesc(); // To get latest series first
}
