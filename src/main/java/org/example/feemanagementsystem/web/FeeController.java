package org.example.feemanagementsystem.web;

import jakarta.validation.Valid;
import org.example.feemanagementsystem.domain.dto.FeeAssignment.CreateFeeAssignmentRequest;
import org.example.feemanagementsystem.domain.dto.FeeAssignment.FeeAssignmentResponse;
import org.example.feemanagementsystem.domain.dto.fee_payment.CreateFeePaymentRequest;
import org.example.feemanagementsystem.domain.dto.fee_payment.FeePaymentResponse;
import org.example.feemanagementsystem.service.FeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fee")
public class FeeController {
    private final FeeService service;

    public FeeController(FeeService service) {
        this.service = service;
    }

    @PostMapping("/assign-fee")
    public ResponseEntity<FeeAssignmentResponse> assign(@Valid @RequestBody CreateFeeAssignmentRequest createFeeAssignmentRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.assign(createFeeAssignmentRequest));
    }

    @GetMapping("/get-fee/{id}")
    public ResponseEntity<FeeAssignmentResponse> getAssignedFee(@PathVariable Long id) {
        return ResponseEntity.ok(service.findAssignedFeeById(id));
    }

    @PostMapping("/pay")
    public ResponseEntity<FeePaymentResponse> pay(@Valid @RequestBody CreateFeePaymentRequest createFeePaymentRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.payByFeeAssigned(createFeePaymentRequest));
    }

    @GetMapping("/payment/{id}")
    public ResponseEntity<FeePaymentResponse> getFeePayed(@PathVariable Long id) {

        return ResponseEntity.ok(service.getFeePayedById(id));
    }

    @GetMapping("/dues/{id}")
    public ResponseEntity<List<FeeAssignmentResponse>> getPendingDuesById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPendingDuesById(id));
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<List<FeePaymentResponse>> getPayedDetailsByStudentId(@PathVariable Long id){
        return ResponseEntity.ok(service.getPayedDetailsByStudentId(id));
    }
}
