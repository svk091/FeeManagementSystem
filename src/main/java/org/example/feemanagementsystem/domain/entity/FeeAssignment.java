package org.example.feemanagementsystem.domain.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class FeeAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fee_type_id", nullable = false)
    private FeeType feeType;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal assignedAmount;

    public FeeAssignment() {
    }

    public FeeAssignment(Student student, FeeType feeType, BigDecimal assignedAmount) {
        this.student = student;
        this.feeType = feeType;
        this.assignedAmount = assignedAmount;
    }

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public FeeType getFeeType() {
        return feeType;
    }

    public BigDecimal getAssignedAmount() {
        return assignedAmount;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setFeeType(FeeType feeType) {
        this.feeType = feeType;
    }

    public void setAssignedAmount(BigDecimal assignedAmount) {
        this.assignedAmount = assignedAmount;
    }
}
