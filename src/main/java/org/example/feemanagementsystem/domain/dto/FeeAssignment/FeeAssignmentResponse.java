package org.example.feemanagementsystem.domain.dto.FeeAssignment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.feemanagementsystem.domain.entity.FeeType;

import java.math.BigDecimal;

public record FeeAssignmentResponse( Long id,  FeeType feeType,BigDecimal assignedAmount) {
}
