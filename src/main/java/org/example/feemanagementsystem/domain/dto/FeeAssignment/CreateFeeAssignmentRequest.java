package org.example.feemanagementsystem.domain.dto.FeeAssignment;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record CreateFeeAssignmentRequest(@NotBlank Long studentId, String feeType, @NotBlank BigDecimal assignedAmount) {
}
