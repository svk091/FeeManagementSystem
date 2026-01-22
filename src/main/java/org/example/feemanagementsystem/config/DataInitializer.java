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


            if (studentRepository.count() == 0) {
                Student s1 = studentRepository.save(new Student("Ramesh", "9876543210"));
                Student s2 = studentRepository.save(new Student("Suresh", "9123456789"));

                FeeAssignment a1 = assignmentRepository.save(
                        new FeeAssignment(s1, FeeType.TUITION, new BigDecimal("5000.00"))
                );
                FeeAssignment a2 = assignmentRepository.save(
                        new FeeAssignment(s2, FeeType.TRANSPORT, new BigDecimal("1000.00"))
                );

                paymentRepository.save(new FeePayment(a1, new BigDecimal("2000.00")));
                paymentRepository.save(new FeePayment(a2, new BigDecimal("1000.00")));

            }
        };
    }
}