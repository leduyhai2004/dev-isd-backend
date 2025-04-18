package com.devteria.identityservice.controller;

import com.devteria.identityservice.dto.request.ApiResponse;
import com.devteria.identityservice.dto.request.ContractDto;
import com.devteria.identityservice.entity.Contract;
import com.devteria.identityservice.service.ContractService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ContractController {
    ContractService contractService;

    @PostMapping
    public ApiResponse<Contract> createOrUpdate(@RequestParam long teacherId, @RequestBody ContractDto dto) {
        return ApiResponse.<Contract>builder()
                .result(contractService.createOrUpdateContract(teacherId, dto))
                .build();
    }

    @GetMapping
    public ApiResponse<Contract> get(@RequestParam long teacherId) {
        return ApiResponse.<Contract>builder()
                .result(contractService.getContract(teacherId))
                .build();
    }

    @DeleteMapping
    public ApiResponse<String> delete(@RequestParam long teacherId) {
        contractService.deleteContract(teacherId);
        return ApiResponse.<String>builder().result("Contract deleted").build();
    }
}
