package com.msvc.ai_scanner.utils.mappers;

import com.msvc.ai_scanner.dto.request.BillDtoWoCreatedAt;
import com.msvc.ai_scanner.model.entities.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface billMapper {
    billMapper INSTANCE = Mappers.getMapper(billMapper.class);

    Bill toEntity(BillDtoWoCreatedAt billDtoWoCreatedAt);
}
