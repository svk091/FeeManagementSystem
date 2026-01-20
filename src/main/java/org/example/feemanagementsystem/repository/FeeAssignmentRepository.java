package org.example.feemanagementsystem.repository;

import org.example.feemanagementsystem.domain.entity.FeeAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface FeeAssignmentRepository extends JpaRepository<FeeAssignment, Long> {
    List<FeeAssignment> findByStudentIdAndAssignedAmountGreaterThan(Long id, BigDecimal amount);
    List<FeeAssignment> findByStudentId(Long studentId);
}
