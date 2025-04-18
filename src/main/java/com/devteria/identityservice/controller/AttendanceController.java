package com.devteria.identityservice.controller;

import com.devteria.identityservice.dto.request.ApiResponse;
import com.devteria.identityservice.entity.Attendance;
import com.devteria.identityservice.service.AttendanceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendances")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AttendanceController {
    AttendanceService attendanceService;

    @PostMapping("/checkin")
    public ApiResponse<String> checkIn(@RequestParam long teacherId) {
        attendanceService.checkIn(teacherId);
        return ApiResponse.<String>builder().result("Checked in successfully").build();
    }

    @PostMapping("/checkout")
    public ApiResponse<String> checkOut(@RequestParam long teacherId) {
        attendanceService.checkOut(teacherId);
        return ApiResponse.<String>builder().result("Checked out successfully").build();
    }

    @GetMapping("/history")
    public ApiResponse<List<Attendance>> getHistory(@RequestParam long teacherId) {
        List<Attendance> history = attendanceService.getAttendanceHistory(teacherId);
        return ApiResponse.<List<Attendance>>builder().result(history).build();
    }
}
