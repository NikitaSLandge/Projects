package com.example.Farm_management.Service.mapper;

import com.example.Farm_management.domain.Supplier;
import com.example.Farm_management.Service.dto.SupplierDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper extends Entitymapper<SupplierDto, Supplier> {

    SupplierDto toDto(Supplier supplier);

    List<SupplierDto> toDto(List<Supplier> suppliers);

    Supplier toEntity(SupplierDto supplierDto);
}
