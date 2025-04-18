package com.devteria.identityservice.repository;

import com.devteria.identityservice.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    Contract findByTeacherId(long teacherId);
}