package com.devteria.identityservice.controller;

import com.devteria.identityservice.dto.request.ApiResponse;
import com.devteria.identityservice.dto.request.LeaveRequestDto;
import com.devteria.identityservice.entity.LeaveRequest;
import com.devteria.identityservice.service.LeaveRequestService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LeaveRequestController {
    LeaveRequestService leaveRequestService;

    @PostMapping("/leave-requests")
    public ApiResponse<LeaveRequest> submit(@RequestParam long teacherId,
                                            @RequestBody LeaveRequestDto dto) {
        LeaveRequest req = leaveRequestService.submitRequest(teacherId, dto);
        return ApiResponse.<LeaveRequest>builder().result(req).build();
    }

    @GetMapping("/leave-requests/history")
    public ApiResponse<List<LeaveRequest>> history(@RequestParam long teacherId) {
        List<LeaveRequest> list = leaveRequestService.getRequestHistory(teacherId);
        return ApiResponse.<List<LeaveRequest>>builder().result(list).build();
    }

    @GetMapping("/admin/leave-requests/pending")
    public ApiResponse<List<LeaveRequest>> pending() {
        List<LeaveRequest> list = leaveRequestService.getPendingRequests();
        return ApiResponse.<List<LeaveRequest>>builder().result(list).build();
    }

    @PostMapping("/admin/leave-requests/{id}/approve")
    public ApiResponse<LeaveRequest> approve(@PathVariable Long id) {
        LeaveRequest req = leaveRequestService.approveRequest(id);
        return ApiResponse.<LeaveRequest>builder().result(req).build();
    }

    @PostMapping("/admin/leave-requests/{id}/reject")
    public ApiResponse<LeaveRequest> reject(@PathVariable Long id,
                                            @RequestParam String reason) {
        LeaveRequest req = leaveRequestService.rejectRequest(id, reason);
        return ApiResponse.<LeaveRequest>builder().result(req).build();
    }
}
