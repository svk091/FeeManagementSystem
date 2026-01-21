package org.example.feemanagementsystem.web;

import jakarta.validation.Valid;
import org.example.feemanagementsystem.domain.dto.FeeAssignment.CreateFeeAssignmentRequest;
import org.example.feemanagementsystem.domain.dto.FeeAssignment.FeeAssignmentResponse;
import org.example.feemanagementsystem.domain.dto.fee_payment.CreateFeePaymentRequest;
import org.example.feemanagementsystem.domain.dto.fee_payment.FeePaymentResponse;
import org.example.feemanagementsystem.service.FeeService;
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
    public FeeAssignmentResponse assign(@Valid @RequestBody CreateFeeAssignmentRequest createFeeAssignmentRequest) {
        return service.assign(createFeeAssignmentRequest);
    }

    @GetMapping("/get-fee/{id}")
    public FeeAssignmentResponse getAssignedFee(@PathVariable Long id) {
        return service.findAssignedFeeById(id);
    }

    @PostMapping("/pay")
    public FeePaymentResponse pay(@Valid @RequestBody CreateFeePaymentRequest createFeePaymentRequest) {
        return service.payByFeeAssigned(createFeePaymentRequest);
    }

    @GetMapping("/payment/{id}")
    public FeePaymentResponse getFeePayed(@PathVariable Long id) {
        return service.getFeePayedById(id);
    }

    @GetMapping("/dues/{id}")
    public List<FeeAssignmentResponse> getPendingDuesById(@PathVariable Long id) {
        return service.getPendingDuesById(id);
    }

    @GetMapping("/payments/{id}")
    public List<FeePaymentResponse> getPayedDetailsByStudentId(@PathVariable Long id){
        return service.getPayedDetailsByStudentId(id);
    }
}
