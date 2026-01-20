package org.example.feemanagementsystem.web;

import org.example.feemanagementsystem.domain.dto.fee_type.CreateFeeTypeRequest;
import org.example.feemanagementsystem.domain.dto.fee_type.FeeTypeResponse;
import org.example.feemanagementsystem.service.FeeTypeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feetype")
public class    FeeTypeController {

    private final FeeTypeService service;

    public FeeTypeController(FeeTypeService service) {
        this.service = service;
    }

    @PostMapping
    public FeeTypeResponse create(@RequestBody CreateFeeTypeRequest createFeeTypeRequest) {
        return service.create(createFeeTypeRequest);
    }

    @GetMapping("/{id}")
    public FeeTypeResponse getFeeTypeById(@PathVariable Long id) {
        return service.getFeeTypeById(id);
    }
}
