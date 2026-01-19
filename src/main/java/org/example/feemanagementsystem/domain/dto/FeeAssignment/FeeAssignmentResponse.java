package org.example.feemanagementsystem.domain.dto.FeeAssignment;

import java.math.BigDecimal;

public record FeeAssignmentResponse(Long id, BigDecimal assignedAmount) {
}
