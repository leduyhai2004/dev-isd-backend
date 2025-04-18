package com.devteria.identityservice.dto.request;

import com.devteria.identityservice.enums.LeaveRequestStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeaveRequestDto {
    LocalDate startDate;
    LocalDate endDate;
    String reason;


}
