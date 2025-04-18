package com.devteria.identityservice.repository;

import com.devteria.identityservice.entity.TeachingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeachingScheduleRepository extends JpaRepository<TeachingSchedule, Long> {
    List<TeachingSchedule> findByTeacherId(long teacherId);
}
