package org.example.feemanagementsystem.domain.dto.fee_payment;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record CreateFeePaymentRequest(@NotBlank Long feeAssignmentId, @NotBlank BigDecimal paidAmount) {
}
