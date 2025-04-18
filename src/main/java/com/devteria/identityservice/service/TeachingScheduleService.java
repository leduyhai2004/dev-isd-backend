package com.devteria.identityservice.service;

import com.devteria.identityservice.dto.request.TeachingScheduleDto;
import com.devteria.identityservice.entity.TeachingSchedule;
import com.devteria.identityservice.exception.AppException;
import com.devteria.identityservice.exception.ErrorCode;
import com.devteria.identityservice.repository.TeachingScheduleRepository;
import com.devteria.identityservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeachingScheduleService {
    TeachingScheduleRepository scheduleRepository;
    UserRepository userRepository;

    public TeachingSchedule addSchedule(long teacherId, TeachingScheduleDto dto) {
        var user = userRepository.findById(teacherId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        TeachingSchedule schedule = TeachingSchedule.builder()
                .teacher(user)
                .dayOfWeek(dto.getDayOfWeek())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .subject(dto.getSubject())
                .location(dto.getLocation())
                .build();
        return scheduleRepository.save(schedule);
    }

    public List<TeachingSchedule> getSchedules(long teacherId) {
        return scheduleRepository.findByTeacherId(teacherId);
    }

    public TeachingSchedule updateSchedule(Long id, TeachingScheduleDto dto) {
        TeachingSchedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SCHEDULE_NOT_FOUND));
        schedule.setDayOfWeek(dto.getDayOfWeek());
        schedule.setStartTime(dto.getStartTime());
        schedule.setEndTime(dto.getEndTime());
        schedule.setSubject(dto.getSubject());
        schedule.setLocation(dto.getLocation());
        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}
