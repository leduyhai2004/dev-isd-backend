package com.devteria.identityservice.dto.request;

import com.devteria.identityservice.enums.ContractStatus;
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
public class ContractDto {
    LocalDate startDate;
    LocalDate endDate;
    Double salary;
    String terms;
    @Enumerated(EnumType.STRING)
    ContractStatus status;
}
