package com.devteria.identityservice.repository;

import com.devteria.identityservice.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByTeacherId(long teacherId);
}
