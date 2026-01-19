package org.example.feemanagementsystem.domain.mapper.student;

import org.example.feemanagementsystem.domain.dto.student.CreateStudentRequest;
import org.example.feemanagementsystem.domain.dto.student.StudentResponse;
import org.example.feemanagementsystem.domain.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public Student toEntity(CreateStudentRequest createStudentRequest) {
        return new Student(createStudentRequest.name(), createStudentRequest.mobileNo());
    }

    public StudentResponse toDto(Student student) {
        return new StudentResponse(student.getId(), student.getName(), student.getMobileNo());
    }
}