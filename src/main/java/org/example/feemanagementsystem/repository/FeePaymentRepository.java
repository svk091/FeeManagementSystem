package org.example.feemanagementsystem.repository;

import org.example.feemanagementsystem.domain.entity.FeePayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeePaymentRepository extends JpaRepository<FeePayment, Long> {
    List<FeePayment> findByFeeAssignmentIdIn(List<Long> feeAssignmentIds);
}
