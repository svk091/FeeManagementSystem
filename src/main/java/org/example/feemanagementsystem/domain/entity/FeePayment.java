package org.example.feemanagementsystem.domain.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class FeePayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fee_assignmet_id", nullable = false)
    private FeeAssignment feeAssignment;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal paidAmount;

    @CreatedDate
    @Column(updatable = false)
    private Instant paidAt;

    public FeePayment() {
    }

    public FeePayment(FeeAssignment feeAssignment, BigDecimal paidAmount) {
        this.feeAssignment = feeAssignment;
        this.paidAmount = paidAmount;
    }

    public Long getId() {
        return id;
    }

    public FeeAssignment getFeeAssignment() {
        return feeAssignment;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public Instant getPaidAt() {
        return paidAt;
    }

    public void setFeeAssignment(FeeAssignment feeAssignment) {
        this.feeAssignment = feeAssignment;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }
}
