package com.devteria.identityservice.service;

import com.devteria.identityservice.dto.request.LeaveRequestDto;
import com.devteria.identityservice.entity.LeaveRequest;
import com.devteria.identityservice.entity.User;
import com.devteria.identityservice.enums.LeaveRequestStatus;
import com.devteria.identityservice.exception.AppException;
import com.devteria.identityservice.exception.ErrorCode;
import com.devteria.identityservice.repository.LeaveRequestRepository;
import com.devteria.identityservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LeaveRequestService {
    LeaveRequestRepository leaveRequestRepository;
    UserRepository userRepository;

    public LeaveRequest submitRequest(long teacherId, LeaveRequestDto dto) {
        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        LeaveRequest req = LeaveRequest.builder()
                .teacher(teacher)
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .reason(dto.getReason())
                .status(LeaveRequestStatus.PENDING)
                .build();
        return leaveRequestRepository.save(req);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public LeaveRequest approveRequest(Long requestId) {
        LeaveRequest req = leaveRequestRepository.findById(requestId)
                .orElseThrow(() -> new AppException(ErrorCode.REQUEST_NOT_FOUND));
        req.setStatus(LeaveRequestStatus.APPROVED);
        return leaveRequestRepository.save(req);
    }


    @PreAuthorize("hasRole('ADMIN')")
    public LeaveRequest rejectRequest(Long requestId, String rejectionReason) {
        LeaveRequest req = leaveRequestRepository.findById(requestId)
                .orElseThrow(() -> new AppException(ErrorCode.REQUEST_NOT_FOUND));
        req.setStatus(LeaveRequestStatus.REJECTED);
        req.setRejectionReason(rejectionReason);
        return leaveRequestRepository.save(req);
    }

    public List<LeaveRequest> getRequestHistory(long teacherId) {
        return leaveRequestRepository.findByTeacherId(teacherId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<LeaveRequest> getPendingRequests(){
        return leaveRequestRepository.findByStatus(LeaveRequestStatus.PENDING);
    }
}
