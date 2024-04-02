package com.example.Farm_management.Service.mapper;

import com.example.Farm_management.domain.Stock;
import com.example.Farm_management.Service.dto.StockDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMapper extends Entitymapper<StockDto, Stock> {

    StockDto toDto(Stock stock);

    List<StockDto> toDto(List<Stock> stocks);

    Stock toEntity(StockDto stockDto);
}
