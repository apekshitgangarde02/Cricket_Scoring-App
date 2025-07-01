package com.example.cricScore.controller;

import com.example.cricScore.model.Series;
import com.example.cricScore.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series")
public class SeriesController { 

    @Autowired
    private SeriesRepository seriesRepository;

    @PostMapping
    public Series createSeries(@RequestBody Series series) {
        return seriesRepository.save(series);
    }

    @GetMapping
    public List<Series> getAllSeries() {
        return seriesRepository.findAllByOrderByIdDesc();
    }

    @GetMapping("/{id}")
    public Series getSeriesById(@PathVariable Long id) {
        return seriesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Series not found"));
    }
}
