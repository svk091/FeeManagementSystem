package org.example.feemanagementsystem.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.feemanagementsystem.domain.dto.FeeAssignment.CreateFeeAssignmentRequest;
import org.example.feemanagementsystem.domain.dto.FeeAssignment.FeeAssignmentResponse;
import org.example.feemanagementsystem.domain.dto.fee_payment.CreateFeePaymentRequest;
import org.example.feemanagementsystem.domain.dto.fee_payment.FeePaymentResponse;
import org.example.feemanagementsystem.domain.entity.FeeAssignment;
import org.example.feemanagementsystem.domain.entity.FeePayment;
import org.example.feemanagementsystem.domain.mapper.fee_assignment.FeeAssignmentMapper;
import org.example.feemanagementsystem.domain.mapper.fee_payment.FeePaymentMapper;
import org.example.feemanagementsystem.repository.FeeAssignmentRepository;
import org.example.feemanagementsystem.repository.FeePaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeeService {
    private final FeeAssignmentRepository feeAssignmentRepository;
    private final FeeAssignmentMapper feeAssignmentMapper;
    private final FeePaymentRepository feePaymentRepository;
    private final FeePaymentMapper feePaymentMapper;

    public FeeService(FeeAssignmentRepository feeAssignmentRepository, FeeAssignmentMapper feeAssignmentMapper, FeePaymentRepository feePaymentRepository, FeePaymentMapper feePaymentMapper) {
        this.feeAssignmentRepository = feeAssignmentRepository;
        this.feeAssignmentMapper = feeAssignmentMapper;
        this.feePaymentRepository = feePaymentRepository;
        this.feePaymentMapper = feePaymentMapper;
    }

    public FeeAssignmentResponse assign(CreateFeeAssignmentRequest createFeeAssignmentRequest) {
        FeeAssignment feeAssignment = feeAssignmentMapper.toEntity(createFeeAssignmentRequest);
        FeeAssignment savedFeeAssignment = feeAssignmentRepository.save(feeAssignment);
        return feeAssignmentMapper.toDto(savedFeeAssignment);
    }

    public FeeAssignmentResponse findAssignedFeeById(Long id) {
        FeeAssignment feeAssignment = feeAssignmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Assigned fee with id: " + id));
        return feeAssignmentMapper.toDto(feeAssignment);
    }


    @Transactional
    public FeePaymentResponse payByFeeAssigned(CreateFeePaymentRequest createFeePaymentRequest) {
        FeeAssignment feeAssignment = feeAssignmentRepository.findById(createFeePaymentRequest.feeAssignmentId())
                .orElseThrow(() -> new EntityNotFoundException("No fee assigned with id: " + createFeePaymentRequest.feeAssignmentId()));

        int compared = feeAssignment.getAssignedAmount().compareTo(createFeePaymentRequest.paidAmount());

        if(compared < 0) {
            throw  new IllegalArgumentException("Overpayment");
        } else  {
            BigDecimal balance = feeAssignment.getAssignedAmount().subtract(createFeePaymentRequest.paidAmount());
            feeAssignment.setAssignedAmount(balance);
            feeAssignmentRepository.save(feeAssignment);
        }

        FeePayment feePayment = feePaymentMapper.toEntity(createFeePaymentRequest);
        FeePayment savedFeePayment =  feePaymentRepository.save(feePayment);

        return feePaymentMapper.toDto(savedFeePayment);

    }

    public FeePaymentResponse getFeePayedById(Long id) {
        FeePayment feePayment = feePaymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No fee payment with id: " + id));
        return feePaymentMapper.toDto(feePayment);
    }

    public List<FeeAssignmentResponse> getPendingDuesById(Long id) {
        List<FeeAssignmentResponse> result = new ArrayList<>();
        List<FeeAssignment> list =  feeAssignmentRepository.findByStudentIdAndAssignedAmountGreaterThan(id, BigDecimal.ZERO);
        for(FeeAssignment item: list) {
            result.add(feeAssignmentMapper.toDto(item));
        }
        return result;
    }

    public List<FeePaymentResponse> getPayedDetailsByStudentId(Long id) {

        List<FeeAssignment> assignments = feeAssignmentRepository.findByStudentId(id);

        List<Long> assignmentIds = new ArrayList<>();
        for(FeeAssignment item: assignments) {
            assignmentIds.add(item.getId());
        }

        List<FeePayment> payments = feePaymentRepository.findByFeeAssignmentIdIn(assignmentIds);

        List<FeePaymentResponse> result = new ArrayList<>();
        for(FeePayment item: payments) {
            result.add(feePaymentMapper.toDto(item));
        }
        return result;
    }

    public List<FeePaymentResponse> getPaymentsByFeeAssignmentId(Long feeAssignmentId) {

        feeAssignmentRepository.findById(feeAssignmentId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "No fee assignment with id: " + feeAssignmentId));

        List<FeePayment> payments =
                feePaymentRepository.findByFeeAssignmentId(feeAssignmentId);

        List<FeePaymentResponse> result = new ArrayList<>();
        for (FeePayment payment : payments) {
            result.add(feePaymentMapper.toDto(payment));
        }
        return result;
    }


}
