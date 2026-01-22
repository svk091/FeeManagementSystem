package org.example.feemanagementsystem.web;


import jakarta.validation.Valid;
import org.example.feemanagementsystem.domain.dto.student.CreateStudentRequest;
import org.example.feemanagementsystem.domain.dto.student.StudentResponse;
import org.example.feemanagementsystem.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StudentResponse> create(@Valid @RequestBody CreateStudentRequest createStudentRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(createStudentRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable("id") Long id) {

        return ResponseEntity.ok(service.getStudentById(id));
    }
}
