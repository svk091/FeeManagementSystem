package org.example.feemanagementsystem.domain.dto.reports;

import java.math.BigDecimal;

public record StudentDuesDto(Long id, String name, BigDecimal dues) {
}
