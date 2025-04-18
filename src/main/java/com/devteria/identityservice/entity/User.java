package com.devteria.identityservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String username;
    String password;
    String firstName;
    String lastName;
    LocalDate dob;
    // thêm thông tin giáo viên
    String email;
    String phone;
    String address;
    String degree;
    LocalDate teachingStartDate;
    @ElementCollection
    Set<String> roles;


    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    Set<Attendance> attendances;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    Set<LeaveRequest> leaveRequests;

    @OneToOne(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    Contract contract;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    Set<TeachingSchedule> schedules;
}
