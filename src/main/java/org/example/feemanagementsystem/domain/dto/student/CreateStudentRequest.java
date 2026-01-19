package org.example.feemanagementsystem.domain.dto.student;

import jakarta.validation.constraints.NotBlank;

public record CreateStudentRequest(@NotBlank String name, @NotBlank String mobileNo) {
}

