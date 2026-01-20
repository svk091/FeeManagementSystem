package org.example.feemanagementsystem.domain.mapper.fee_assignment;

import jakarta.persistence.EntityNotFoundException;
import org.example.feemanagementsystem.domain.dto.FeeAssignment.CreateFeeAssignmentRequest;
import org.example.feemanagementsystem.domain.dto.FeeAssignment.FeeAssignmentResponse;
import org.example.feemanagementsystem.domain.entity.FeeAssignment;
import org.example.feemanagementsystem.domain.entity.FeeType;
import org.example.feemanagementsystem.domain.entity.Student;
import org.example.feemanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Component;


@Component
public class FeeAssignmentMapper {
    private final StudentRepository studentRepository;

    public FeeAssignmentMapper(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public FeeAssignment toEntity(CreateFeeAssignmentRequest createFeeAssignmentRequest) {
        Student student = studentRepository.findById(createFeeAssignmentRequest.studentId())
                .orElseThrow(() -> new EntityNotFoundException("No student with id: " + createFeeAssignmentRequest.studentId()));
        return new FeeAssignment(student,FeeType.valueOf(createFeeAssignmentRequest.feeType().toUpperCase()), createFeeAssignmentRequest.assignedAmount());
    }

    public FeeAssignmentResponse toDto(FeeAssignment feeAssignment) {
        return new FeeAssignmentResponse(feeAssignment.getId(),feeAssignment.getFeeType(), feeAssignment.getAssignedAmount());
    }
}
