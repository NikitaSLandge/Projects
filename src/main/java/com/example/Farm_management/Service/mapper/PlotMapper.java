package com.example.Farm_management.Service.mapper;

import com.example.Farm_management.domain.Plot;
import com.example.Farm_management.Service.dto.PlotDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlotMapper extends Entitymapper<PlotDto, Plot> {

    PlotDto toDto(Plot plot);

    List<PlotDto> toDto(List<Plot> plots);

    Plot toEntity(PlotDto plotDto);
}
