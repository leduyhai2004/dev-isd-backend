package com.devteria.identityservice.service;

import com.devteria.identityservice.dto.request.ContractDto;
import com.devteria.identityservice.entity.Contract;
import com.devteria.identityservice.exception.AppException;
import com.devteria.identityservice.exception.ErrorCode;
import com.devteria.identityservice.repository.ContractRepository;
import com.devteria.identityservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ContractService {
    ContractRepository contractRepository;
    UserRepository userRepository;

    public Contract createOrUpdateContract(long teacherId, ContractDto dto) {
        var user = userRepository.findById(teacherId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Contract contract = contractRepository.findByTeacherId(teacherId);
        if (contract == null) {
            contract = Contract.builder()
                    .teacher(user)
                    .build();
        }
        contract.setStartDate(dto.getStartDate());
        contract.setEndDate(dto.getEndDate());
        contract.setSalary(dto.getSalary());
        contract.setTerms(dto.getTerms());
        contract.setStatus(dto.getStatus());
        return contractRepository.save(contract);
    }

    public Contract getContract(long teacherId) {
        return contractRepository.findByTeacherId(teacherId);
    }

    public void deleteContract(long teacherId) {
        Contract contract = contractRepository.findByTeacherId(teacherId);
        if (contract != null) contractRepository.delete(contract);
    }
}
