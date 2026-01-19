package org.example.feemanagementsystem.repository;

import org.example.feemanagementsystem.domain.entity.FeePayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeePaymentRepository extends JpaRepository<FeePayment, Long> {
}
