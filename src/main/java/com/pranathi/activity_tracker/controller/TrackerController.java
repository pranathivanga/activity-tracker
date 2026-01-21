package com.pranathi.activity_tracker.controller;

import com.pranathi.activity_tracker.model.Activity;
import com.pranathi.activity_tracker.model.DailyLog;
import com.pranathi.activity_tracker.repo.ActivityRepository;
import com.pranathi.activity_tracker.repo.DailyLogRepository;
import com.pranathi.activity_tracker.service.HeatmapService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TrackerController {

    private final ActivityRepository activityRepo;
    private final DailyLogRepository logRepo;
    private final HeatmapService heatmapService;

    public TrackerController(ActivityRepository a, DailyLogRepository d, HeatmapService h) {
        this.activityRepo = a;
        this.logRepo = d;
        this.heatmapService = h;
    }

    @PostMapping("/activities")
    public Activity addActivity(@RequestBody Activity activity) {
        return activityRepo.save(activity);
    }

    @PostMapping("/log")
    public DailyLog log(@RequestBody DailyLog log) {
        return logRepo.save(log);
    }

    @GetMapping("/heatmap")
    public Map<LocalDate, Integer> heatmap(
            @RequestParam int year,
            @RequestParam int month) {

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<DailyLog> logs = logRepo.findByDateBetween(start, end);

        Map<LocalDate, Integer> minutesPerDay = new HashMap<>();

        for (DailyLog log : logs) {
            minutesPerDay.merge(
                    log.getDate(),
                    log.getMinutes(),
                    Integer::sum
            );
        }

        Map<LocalDate, Integer> result = new HashMap<>();
        for (Map.Entry<LocalDate, Integer> e : minutesPerDay.entrySet()) {
            result.put(e.getKey(), heatmapService.intensity(e.getValue()));
        }

        return result;
    }
}
