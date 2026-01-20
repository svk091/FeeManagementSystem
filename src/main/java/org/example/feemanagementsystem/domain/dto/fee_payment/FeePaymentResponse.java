package org.example.feemanagementsystem.domain.dto.fee_payment;

import java.math.BigDecimal;
import java.time.Instant;

public record FeePaymentResponse(Long id, Instant date, BigDecimal paidAmount) {
}
