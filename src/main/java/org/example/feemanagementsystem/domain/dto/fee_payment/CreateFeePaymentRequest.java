package org.example.feemanagementsystem.domain.dto.fee_payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateFeePaymentRequest(@NotNull Long feeAssignmentId, @NotNull @Positive BigDecimal paidAmount) {
}
