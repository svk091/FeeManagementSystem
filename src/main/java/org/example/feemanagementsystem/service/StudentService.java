package org.example.feemanagementsystem.service;


import jakarta.persistence.EntityNotFoundException;
import org.example.feemanagementsystem.domain.dto.student.CreateStudentRequest;
import org.example.feemanagementsystem.domain.dto.student.StudentResponse;
import org.example.feemanagementsystem.domain.entity.Student;
import org.example.feemanagementsystem.domain.mapper.student.StudentMapper;
import org.example.feemanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository repo;
    private final StudentMapper mapper;

    public StudentService(StudentRepository repo, StudentMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public StudentResponse getStudentById(Long id) {
        Student student = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("No student with id: " + id));
        return mapper.toDto(student);
    }

    public StudentResponse create(CreateStudentRequest createStudentRequest) {
        Student student = mapper.toEntity(createStudentRequest);
        Student savedStudent = repo.save(student);
        return mapper.toDto(savedStudent);
    }
}
