package org.example.feemanagementsystem.web;

import org.example.feemanagementsystem.domain.dto.FeeAssignment.CreateFeeAssignmentRequest;
import org.example.feemanagementsystem.domain.dto.FeeAssignment.FeeAssignmentResponse;
import org.example.feemanagementsystem.domain.dto.fee_payment.CreateFeePaymentRequest;
import org.example.feemanagementsystem.domain.dto.fee_payment.FeePaymentResponse;
import org.example.feemanagementsystem.service.FeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fee")
public class FeeController {
    private final FeeService service;

    public FeeController(FeeService service) {
        this.service = service;
    }

    @PostMapping("/assign-fee")
    public FeeAssignmentResponse assign(@RequestBody CreateFeeAssignmentRequest createFeeAssignmentRequest) {
        return service.assign(createFeeAssignmentRequest);
    }

    @GetMapping("/get-fee/{id}")
    public FeeAssignmentResponse getAssignedFee(@PathVariable Long id) {
        return service.findAssignedFeeById(id);
    }

    @PostMapping("/pay")
    public FeePaymentResponse pay(@RequestBody CreateFeePaymentRequest createFeePaymentRequest) {
        return service.payByFeeAssigned(createFeePaymentRequest);
    }

    @GetMapping("/payment/{id}")
    public FeePaymentResponse getFeePayed(@PathVariable Long id) {
        return service.getFeePayedById(id);
    }
}
