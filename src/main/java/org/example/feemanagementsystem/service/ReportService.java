package org.example.feemanagementsystem.service;

import org.example.feemanagementsystem.domain.dto.reports.StudentDuesDto;
import org.example.feemanagementsystem.domain.entity.FeeAssignment;
import org.example.feemanagementsystem.domain.entity.Student;
import org.example.feemanagementsystem.repository.FeeAssignmentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    private final FeeAssignmentRepository feeAssignmentRepository;

    public ReportService(FeeAssignmentRepository feeAssignmentRepository) {
        this.feeAssignmentRepository = feeAssignmentRepository;
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
}
