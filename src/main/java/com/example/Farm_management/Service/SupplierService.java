package com.example.Farm_management.Service;

import com.example.Farm_management.Service.dto.SupplierDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SupplierService {
    SupplierDto save(SupplierDto supplierDto);

    List<SupplierDto> getAllSuppliers();


    Optional<SupplierDto> getSupplier(Long id);

    SupplierDto update(SupplierDto supplierDto);

    Optional<SupplierDto> partialUpdate(SupplierDto supplierDto);

    void delete(Long id);
}
