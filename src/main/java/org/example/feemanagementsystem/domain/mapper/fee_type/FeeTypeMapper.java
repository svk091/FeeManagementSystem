package org.example.feemanagementsystem.domain.mapper.fee_type;

import org.example.feemanagementsystem.domain.dto.fee_type.CreateFeeTypeRequest;
import org.example.feemanagementsystem.domain.dto.fee_type.FeeTypeResponse;
import org.example.feemanagementsystem.domain.entity.FeeType;
import org.springframework.stereotype.Component;

@Component
public class FeeTypeMapper {
    public FeeTypeResponse toDto(FeeType feeType) {
        return new FeeTypeResponse(feeType.getId(), feeType.getName(), feeType.getDescription());
    }

    public FeeType toEntity(CreateFeeTypeRequest createFeeTypeRequest) {
        return new FeeType(createFeeTypeRequest
                .name(), createFeeTypeRequest.description());
    }
}
