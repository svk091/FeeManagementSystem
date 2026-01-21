package org.example.feemanagementsystem.web;


import jakarta.validation.Valid;
import org.example.feemanagementsystem.domain.dto.student.CreateStudentRequest;
import org.example.feemanagementsystem.domain.dto.student.StudentResponse;
import org.example.feemanagementsystem.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public StudentResponse create(@Valid @RequestBody CreateStudentRequest createStudentRequest) {
        return service.create(createStudentRequest);
    }

    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable Long id) {
        return service.getStudentById(id);
    }
}
