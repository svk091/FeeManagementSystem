package org.example.feemanagementsystem.domain.dto.FeeAssignment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateFeeAssignmentRequest(@NotNull Long studentId, @NotBlank String feeType, @NotNull @Positive BigDecimal assignedAmount) {
}
