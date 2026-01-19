package org.example.feemanagementsystem.domain.mapper.fee_assignment;

import jakarta.persistence.EntityNotFoundException;
import org.example.feemanagementsystem.domain.dto.FeeAssignment.CreateFeeAssignmentRequest;
import org.example.feemanagementsystem.domain.dto.FeeAssignment.FeeAssignmentResponse;
import org.example.feemanagementsystem.domain.entity.FeeAssignment;
import org.example.feemanagementsystem.domain.entity.FeeType;
import org.example.feemanagementsystem.domain.entity.Student;
import org.example.feemanagementsystem.repository.FeeTypeRepository;
import org.example.feemanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Component;


@Component
public class FeeAssignmentMapper {
    private final StudentRepository studentRepository;
    private final FeeTypeRepository feeTypeRepository;

    public FeeAssignmentMapper(StudentRepository studentRepository, FeeTypeRepository feeTypeRepository) {
        this.studentRepository = studentRepository;
        this.feeTypeRepository = feeTypeRepository;
    }

    public FeeAssignment toEntity(CreateFeeAssignmentRequest createFeeAssignmentRequest) {
        Student student = studentRepository.findById(createFeeAssignmentRequest.studentId())
                .orElseThrow(() -> new EntityNotFoundException("No student with id: " + createFeeAssignmentRequest.studentId()));
        FeeType feeType = feeTypeRepository.findById(createFeeAssignmentRequest.feeTypeId())
                .orElseThrow(() -> new EntityNotFoundException("No fee type with id: " + createFeeAssignmentRequest.feeTypeId()));
        return new FeeAssignment(student, feeType, createFeeAssignmentRequest.assignedAmount());
    }

    public FeeAssignmentResponse toDto(FeeAssignment feeAssignment) {
        return new FeeAssignmentResponse(feeAssignment.getId(), feeAssignment.getAssignedAmount());
    }
}
