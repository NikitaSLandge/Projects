package com.example.Farm_management.Service.Impl;

import com.example.Farm_management.Service.SupplierService;
import com.example.Farm_management.Service.dto.SupplierDto;
import com.example.Farm_management.Service.mapper.SupplierMapper;
import com.example.Farm_management.domain.Supplier;
import com.example.Farm_management.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }


    @Override
    public SupplierDto save(SupplierDto supplierDto) {
        Supplier supplier = supplierMapper.toEntity(supplierDto);
        supplier = supplierRepository.save(supplier);
        return supplierMapper.toDto(supplier);
    }

    @Override
    public List<SupplierDto> getAllSuppliers() {
       //Supplier supplier = supplierMapper.toEntity((supplierDto));
       List<Supplier> savedSupplier = (List<Supplier>) supplierRepository.findAll();
       return supplierMapper.toDto(savedSupplier);
    }

    @Override
    public Optional<SupplierDto> getSupplier(Long id) {
        return supplierRepository.findById(id).map(supplierMapper::toDto);
    }

    @Override
    public SupplierDto update(SupplierDto supplierDto) {
        Supplier supplier = supplierMapper.toEntity(supplierDto);
        supplier = supplierRepository.save(supplier);
        return supplierMapper.toDto(supplier);
    }



    @Override
    public Optional<SupplierDto> partialUpdate(SupplierDto supplierDto) {
        return supplierRepository.findById(supplierDto.getId()).map(existingSupplier ->{
            supplierMapper.partialUpdate(existingSupplier, supplierDto);
            return existingSupplier;
        }).map(supplierRepository::save).map(supplierMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        supplierRepository.deleteById(id);
    }
}
