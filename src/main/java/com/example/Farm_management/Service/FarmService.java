package com.example.Farm_management.Service;

import com.example.Farm_management.Service.dto.FarmDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FarmService {


    FarmDto addFarm(FarmDto farmDto);

    List<FarmDto> getAllfarms(FarmDto farmDto);
    

    Optional<FarmDto> getFarmById(Long id);

    void deleteFarmById(Long id);

    FarmDto updateFarm(FarmDto farmDto);

    Optional<FarmDto> partialUpdate(FarmDto farmDto);

    void checkIfFarmDeletable(Long id);
}
