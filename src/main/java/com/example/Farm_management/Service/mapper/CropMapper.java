package com.example.Farm_management.Service.mapper;

import com.example.Farm_management.domain.Crop;
import com.example.Farm_management.Service.dto.CropDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CropMapper extends Entitymapper<CropDto, Crop> {

    CropDto toDto(Crop crop);

    List<CropDto> toDto(List<Crop> crops);

    Crop toEntity(CropDto cropDto);
}
