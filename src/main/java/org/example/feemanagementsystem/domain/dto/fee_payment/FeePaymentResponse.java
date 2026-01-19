package org.example.feemanagementsystem.domain.dto.fee_payment;

import java.math.BigDecimal;

public record FeePaymentResponse(Long id, BigDecimal paidAmount) {
}
