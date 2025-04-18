package com.devteria.identityservice.service;

import com.devteria.identityservice.entity.Attendance;
import com.devteria.identityservice.entity.User;
import com.devteria.identityservice.enums.AttendanceStatus;
import com.devteria.identityservice.exception.AppException;
import com.devteria.identityservice.exception.ErrorCode;
import com.devteria.identityservice.repository.AttendanceRepository;
import com.devteria.identityservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AttendanceService {
    AttendanceRepository attendanceRepository;
    UserRepository userRepository;


    public void checkIn(long teacherId) {
        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        LocalDate today = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();
        Attendance attendance = Attendance.builder()
                .teacher(teacher)
                .date(today)
                .checkInTime(now)
                .status(now.toLocalTime().isAfter(LocalTime.of(8, 0)) ? AttendanceStatus.LATE : AttendanceStatus.ON_TIME)
                .build();
        attendanceRepository.save(attendance);
    }

    public void checkOut(long teacherId) {
        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        LocalDate today = LocalDate.now();
        Attendance attendance = attendanceRepository.findByTeacherId(teacherId).stream()
                .filter(a -> a.getDate().equals(today) && a.getCheckOutTime() == null)
                .findFirst()
                .orElseThrow(() -> new AppException(ErrorCode.ATTENDANCE_NOT_FOUND));
        attendance.setCheckOutTime(LocalDateTime.now());
        attendanceRepository.save(attendance);
    }
    public List<Attendance> getAttendanceHistory(long teacherId) {
        return attendanceRepository.findByTeacherId(teacherId);
    }
}
