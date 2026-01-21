package com.pranathi.activity_tracker.service;

import org.springframework.stereotype.Service;

@Service
public class HeatmapService {
    public int intensity(int minutes) {
        if (minutes == 0) return 0;
        if (minutes < 30) return 1;
        if (minutes < 60) return 2;
        return 3;
    }
}
