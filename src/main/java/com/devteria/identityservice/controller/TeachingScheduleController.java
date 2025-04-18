package com.devteria.identityservice.controller;

import com.devteria.identityservice.dto.request.ApiResponse;
import com.devteria.identityservice.dto.request.TeachingScheduleDto;
import com.devteria.identityservice.entity.TeachingSchedule;
import com.devteria.identityservice.service.TeachingScheduleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeachingScheduleController {
    TeachingScheduleService scheduleService;

    @PostMapping
    public ApiResponse<TeachingSchedule> create(@RequestParam long teacherId, @RequestBody TeachingScheduleDto dto) {
        return ApiResponse.<TeachingSchedule>builder()
                .result(scheduleService.addSchedule(teacherId, dto))
                .build();
    }

    @GetMapping
    public ApiResponse<List<TeachingSchedule>> list(@RequestParam long teacherId) {
        return ApiResponse.<List<TeachingSchedule>>builder()
                .result(scheduleService.getSchedules(teacherId))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<TeachingSchedule> update(@PathVariable Long id, @RequestBody TeachingScheduleDto dto) {
        return ApiResponse.<TeachingSchedule>builder()
                .result(scheduleService.updateSchedule(id, dto))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ApiResponse.<String>builder().result("Schedule deleted").build();
    }
}
