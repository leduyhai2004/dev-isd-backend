package com.devteria.identityservice.entity;

import com.devteria.identityservice.enums.ContractStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDate startDate;
    LocalDate endDate;
    Double salary;
    String terms;

    @Enumerated(EnumType.STRING)
    ContractStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", unique = true)
    User teacher;
}
