package com.pranathi.activity_tracker.repo;

import com.pranathi.activity_tracker.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {}
