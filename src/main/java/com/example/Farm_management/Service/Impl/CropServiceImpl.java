package com.example.Farm_management.Service.Impl;

import com.example.Farm_management.Service.CropService;
import com.example.Farm_management.Service.dto.CropDto;
import com.example.Farm_management.Service.mapper.CropMapper;
import com.example.Farm_management.domain.Crop;
import com.example.Farm_management.domain.Plot;
import com.example.Farm_management.repository.CropRepository;
import com.example.Farm_management.repository.StockRepository;
import com.example.Farm_management.web.exception.BadRequestAlertException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CropServiceImpl implements CropService {

    private final CropRepository cropRepository;
    private final StockRepository stockRepository;

    private final CropMapper cropMapper;

    public CropServiceImpl(CropRepository cropRepository, StockRepository stockRepository, CropMapper cropMapper) {
        this.cropRepository = cropRepository;
        this.stockRepository = stockRepository;
        this.cropMapper = cropMapper;
    }

    @Override
    public CropDto save(CropDto cropDto) {
        Crop crop = cropMapper.toEntity(cropDto);
        crop = cropRepository.save(crop);
        return cropMapper.toDto(crop);
    }

    @Override
    public CropDto update(CropDto cropDto) {
        Crop crop = cropMapper.toEntity(cropDto);
        crop = cropRepository.save(crop);
        return cropMapper.toDto(crop);
    }

    @Override
    public Optional<CropDto> partialUpdate(CropDto cropDto) {
        return cropRepository.findById(cropDto.getId()).map(existingCrops -> {
            cropMapper.partialUpdate(existingCrops, cropDto);
            return existingCrops;
        }).map(cropRepository::save).map(cropMapper::toDto);
    }

    @Override
    public List<CropDto> getAllCrops() {
        List<Crop> crops = (List<Crop>) cropRepository.findAll();
        return cropMapper.toDto(crops);
    }

    @Override
    public Optional<CropDto> findOne(Long id) {
        return cropRepository.findById(id).map(cropMapper::toDto);
    }

    @Override
    @Transactional
    public void delete(Long id) {
            Crop crop = cropRepository.findById(id)
                    .orElseThrow(() -> new BadRequestAlertException("Crop not found with id: " + id));

            // Delete associated stock entities before deleting the crop
            stockRepository.deleteByCropId(id);

            // Now delete the crop
            cropRepository.deleteById(id);
        }

    @Override
    public List<CropDto> getCropByName(String name) {
        List<Crop> crp=  cropRepository.getCropByName(name + "%");
        return cropMapper.toDto(crp);
    }


}



