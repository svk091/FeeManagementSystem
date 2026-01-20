package org.example.feemanagementsystem.domain.mapper.fee_payment;

import jakarta.persistence.EntityNotFoundException;
import org.example.feemanagementsystem.domain.dto.fee_payment.CreateFeePaymentRequest;
import org.example.feemanagementsystem.domain.dto.fee_payment.FeePaymentResponse;
import org.example.feemanagementsystem.domain.entity.FeeAssignment;
import org.example.feemanagementsystem.domain.entity.FeePayment;
import org.example.feemanagementsystem.domain.mapper.fee_assignment.FeeAssignmentMapper;
import org.example.feemanagementsystem.repository.FeeAssignmentRepository;
import org.springframework.stereotype.Component;

@Component
public class FeePaymentMapper {
    private final FeeAssignmentRepository feeAssignmentRepository;

    public FeePaymentMapper(FeeAssignmentRepository feeAssignmentRepository) {
        this.feeAssignmentRepository = feeAssignmentRepository;
    }


    public FeePayment toEntity(CreateFeePaymentRequest createFeePaymentRequest) {
        FeeAssignment feeAssignment = feeAssignmentRepository.findById(createFeePaymentRequest.feeAssignmentId())
                .orElseThrow(() -> new EntityNotFoundException("No fee assigned with id: " + createFeePaymentRequest.feeAssignmentId()));
        return new FeePayment(feeAssignment, createFeePaymentRequest.paidAmount());
    }

    public FeePaymentResponse toDto(FeePayment feePayment) {
        return new FeePaymentResponse(feePayment.getId(),feePayment.getPaidAt(), feePayment.getPaidAmount());
    }
}
