package org.example.feemanagementsystem.domain.dto.fee_type;

import jakarta.validation.constraints.NotBlank;

public record CreateFeeTypeRequest(@NotBlank String name, @NotBlank String description) {
}
