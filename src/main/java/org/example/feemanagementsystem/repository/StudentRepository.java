package org.example.feemanagementsystem.repository;

import org.example.feemanagementsystem.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
