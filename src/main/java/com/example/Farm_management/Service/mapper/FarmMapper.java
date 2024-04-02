package com.example.Farm_management.Service.mapper;

import com.example.Farm_management.domain.Farm;
import com.example.Farm_management.Service.dto.FarmDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FarmMapper extends Entitymapper<FarmDto, Farm> {

    FarmDto toDto(Farm farm);

    List<FarmDto> toDto(List<Farm> farms);

    Farm toEntity(FarmDto farmDto);
}
