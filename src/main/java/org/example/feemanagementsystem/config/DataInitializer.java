package org.example.feemanagementsystem.config;

import org.example.feemanagementsystem.domain.entity.*;
import org.example.feemanagementsystem.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            StudentRepository studentRepository,
            FeeAssignmentRepository assignmentRepository,
            FeePaymentRepository paymentRepository) {

        return args -> {

            if (studentRepository.count() > 0) {
                return;
            }

            Student s1 = studentRepository.save(new Student("Ramesh", "9876543210"));
            Student s2 = studentRepository.save(new Student("Suresh", "9123456789"));
            Student s3 = studentRepository.save(new Student("John", "9988776655"));
            Student s4 = studentRepository.save(new Student("Anita", "9090909090"));

            FeeAssignment s1Tuition = assignmentRepository.save(
                    new FeeAssignment(s1, FeeType.TUITION, new BigDecimal("5000.00"))
            );
            FeeAssignment s1Hostel = assignmentRepository.save(
                    new FeeAssignment(s1, FeeType.HOSTEL, new BigDecimal("3000.00"))
            );

            FeeAssignment s2Transport = assignmentRepository.save(
                    new FeeAssignment(s2, FeeType.TRANSPORT, new BigDecimal("1000.00"))
            );

            FeeAssignment s3Tuition = assignmentRepository.save(
                    new FeeAssignment(s3, FeeType.TUITION, new BigDecimal("6000.00"))
            );
            FeeAssignment s3Transport = assignmentRepository.save(
                    new FeeAssignment(s3, FeeType.TRANSPORT, new BigDecimal("1200.00"))
            );

            FeeAssignment s4Tuition = assignmentRepository.save(
                    new FeeAssignment(s4, FeeType.TUITION, new BigDecimal("5500.00"))
            );

            paymentRepository.save(new FeePayment(s1Tuition, new BigDecimal("2000.00")));
            paymentRepository.save(new FeePayment(s1Tuition, new BigDecimal("1000.00")));

            paymentRepository.save(new FeePayment(s1Hostel, new BigDecimal("3000.00")));

            paymentRepository.save(new FeePayment(s2Transport, new BigDecimal("1000.00")));

            paymentRepository.save(new FeePayment(s3Tuition, new BigDecimal("4000.00")));

            paymentRepository.save(new FeePayment(s4Tuition, new BigDecimal("5500.00")));
        };
    }
}
