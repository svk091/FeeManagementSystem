package org.example.feemanagementsystem.domain.dto.FeeAssignment;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record CreateFeeAssignmentRequest(@NotBlank Long studentId, @NotBlank Long feeTypeId, @NotBlank BigDecimal assignedAmount) {
}
