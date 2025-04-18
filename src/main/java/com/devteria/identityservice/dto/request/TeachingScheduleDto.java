package com.devteria.identityservice.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.DayOfWeek;
import java.time.LocalTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeachingScheduleDto {
    DayOfWeek dayOfWeek;
    LocalTime startTime;
    LocalTime endTime;
    String subject;
    String location;
}
