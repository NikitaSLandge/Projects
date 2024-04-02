package com.example.Farm_management.Service;

import com.example.Farm_management.Service.dto.CropDto;

import java.util.List;
import java.util.Optional;

public interface CropService {

    CropDto save(CropDto cropDto);

    CropDto update(CropDto cropDto);

   Optional<CropDto> partialUpdate(CropDto cropDto);

    List<CropDto> getAllCrops();

    Optional<CropDto> findOne(Long id);

    void delete(Long id);

   List<CropDto> getCropByName(String name);
}
