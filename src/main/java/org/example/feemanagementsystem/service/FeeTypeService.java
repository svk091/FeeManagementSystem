package org.example.feemanagementsystem.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.feemanagementsystem.domain.dto.fee_type.CreateFeeTypeRequest;
import org.example.feemanagementsystem.domain.dto.fee_type.FeeTypeResponse;
import org.example.feemanagementsystem.domain.entity.FeeType;
import org.example.feemanagementsystem.domain.mapper.fee_type.FeeTypeMapper;
import org.example.feemanagementsystem.repository.FeeTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class FeeTypeService {

    private final FeeTypeRepository repository;
    private final FeeTypeMapper mapper;

    public FeeTypeService(FeeTypeRepository repository, FeeTypeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public FeeTypeResponse create(CreateFeeTypeRequest createFeeTypeRequest) {
        FeeType feeType = mapper.toEntity(createFeeTypeRequest);
        FeeType savedFeeType = repository.save(feeType);
        return mapper.toDto(savedFeeType);
    }

    public FeeTypeResponse getFeeTypeById(Long id) {
        FeeType feeType = repository.findById(id).orElseThrow(() ->  new EntityNotFoundException("No fee type with id: " + id));
        return mapper.toDto(feeType);
    }
}
