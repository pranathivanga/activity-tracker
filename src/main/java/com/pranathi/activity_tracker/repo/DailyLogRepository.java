package com.pranathi.activity_tracker.repo;

import com.pranathi.activity_tracker.model.DailyLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DailyLogRepository extends JpaRepository<DailyLog, Long> {

    List<DailyLog> findByDateBetween(LocalDate start, LocalDate end);
}