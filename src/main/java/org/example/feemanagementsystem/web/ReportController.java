package org.example.feemanagementsystem.web;


import org.example.feemanagementsystem.domain.dto.reports.StudentDuesDto;
import org.example.feemanagementsystem.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/getAllDues")
    public ResponseEntity<List<StudentDuesDto>> getAllDues() {

        return ResponseEntity.ok(reportService.getAllDues());
    }
}
