package org.example.feemanagementsystem.service;

import org.example.feemanagementsystem.domain.dto.fee_payment.FeePaymentResponse;
import org.example.feemanagementsystem.domain.dto.reports.StudentDuesDto;
import org.example.feemanagementsystem.domain.entity.FeeAssignment;
import org.example.feemanagementsystem.domain.entity.FeePayment;
import org.example.feemanagementsystem.domain.entity.Student;
import org.example.feemanagementsystem.domain.mapper.fee_payment.FeePaymentMapper;
import org.example.feemanagementsystem.repository.FeeAssignmentRepository;
import org.example.feemanagementsystem.repository.FeePaymentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    private final FeeAssignmentRepository feeAssignmentRepository;
    private final FeePaymentRepository feePaymentRepository;
    private final FeePaymentMapper feePaymentMapper;

    public ReportService(FeeAssignmentRepository feeAssignmentRepository, FeePaymentRepository feePaymentRepository, FeePaymentMapper feePaymentMapper) {
        this.feeAssignmentRepository = feeAssignmentRepository;
        this.feePaymentRepository = feePaymentRepository;
        this.feePaymentMapper = feePaymentMapper;
    }

    public List<StudentDuesDto> getAllDues() {


        List<FeeAssignment> dues =
                feeAssignmentRepository.findByAssignedAmountGreaterThan(BigDecimal.ZERO);

        Map<Long, StudentDuesDto> map = new HashMap<>();

        for (FeeAssignment fa : dues) {

            Student student = fa.getStudent();
            Long studentId = student.getId();

            map.computeIfAbsent(studentId, id ->
                    new StudentDuesDto(
                            student.getId(),
                            student.getName(),
                            BigDecimal.ZERO
                    )
            );

            map.compute(
                    studentId,
                    (k, current) -> new StudentDuesDto(
                            current.id(),
                            current.name(),
                            current.dues().add(fa.getAssignedAmount())
                    )
            );
        }

        return new ArrayList<>(map.values());
    }

    public List<FeePaymentResponse> getAllPayments() {
        List<FeePaymentResponse> result = new ArrayList<>();
        List<FeePayment> list =  feePaymentRepository.findAll();
        for(FeePayment item: list) {
            result.add(feePaymentMapper.toDto(item));
        }
        return result;
    }
}
