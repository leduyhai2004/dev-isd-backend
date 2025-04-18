package com.devteria.identityservice.repository;

import com.devteria.identityservice.entity.LeaveRequest;
import com.devteria.identityservice.enums.LeaveRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByStatus(LeaveRequestStatus status);
    List<LeaveRequest> findByTeacherId(long teacherId);
}
