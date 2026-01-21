package com.pranathi.activity_tracker.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class DailyLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private int minutes;

    @ManyToOne
    private Activity activity;
}
