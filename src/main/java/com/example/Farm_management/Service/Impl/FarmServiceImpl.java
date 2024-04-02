package com.example.Farm_management.Service.Impl;

import com.example.Farm_management.domain.Farm;
import com.example.Farm_management.repository.FarmRepository;
import com.example.Farm_management.Service.FarmService;
import com.example.Farm_management.Service.mapper.FarmMapper;
import com.example.Farm_management.Service.dto.FarmDto;
import com.example.Farm_management.web.exception.BadRequestAlertException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;

    public FarmServiceImpl(FarmRepository farmRepository, FarmMapper farmMapper) {
        this.farmRepository = farmRepository;
        this.farmMapper = farmMapper;
    }


    @Override
    public FarmDto addFarm(FarmDto farmDto) {
        Farm farm = farmMapper.toEntity(farmDto);
        Farm savedfarm = farmRepository.save(farm);
        return farmMapper.toDto(savedfarm);
    }

    @Override
    public List<FarmDto> getAllfarms(FarmDto farmDto) {
        Farm farm = farmMapper.toEntity(farmDto);
        List<Farm> farm1 = (List<Farm>) farmRepository.findAll();
        return farmMapper.toDto(farm1);
    }

    @Override
    public Optional<FarmDto> getFarmById(Long id) {
        return farmRepository.findById(id).map(farmMapper::toDto);

    }

    @Override
    public void deleteFarmById(Long id) {
        farmRepository.deleteById(id);
    }

    @Override
    public FarmDto updateFarm(FarmDto farmDto) {
        Farm farm = farmMapper.toEntity(farmDto);
        Optional<Farm> existingUser = farmRepository.findById(farmDto.getId());
        farm = farmRepository.save(farm);

        return farmMapper.toDto(farm);
    }


    @Override
    public Optional<FarmDto> partialUpdate(FarmDto farmDto) {
        return farmRepository.findById(farmDto.getId()).map(existingFarm -> {
            farmMapper.partialUpdate(existingFarm, farmDto);
            return existingFarm;
        }).map(farmRepository::save).map(farmMapper::toDto);
    }

    @Override
    public void checkIfFarmDeletable(Long id) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        if (farm.getPlot() != null && !farm.getPlot().isEmpty()) {
            throw new BadRequestAlertException("Cannot delete farm with associated plots.");
        }
    }
}

