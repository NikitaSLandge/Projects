package com.example.Farm_management.web.rest;

import com.example.Farm_management.Service.FarmService;
import com.example.Farm_management.repository.FarmRepository;
import com.example.Farm_management.repository.UserRepository;
import com.example.Farm_management.web.exception.BadRequestAlertException;
import com.example.Farm_management.Service.dto.FarmDto;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class FarmResource {

    private final FarmService farmService;
    private final FarmRepository farmRepository;
    private final UserRepository userRepository;

    public FarmResource(FarmService farmService, FarmRepository farmRepository, UserRepository userRepository) {
        this.farmService = farmService;
        this.farmRepository = farmRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/farm")
    public ResponseEntity<FarmDto> createFarm(@RequestBody FarmDto farmDto) {
        if (farmDto.getId() != null) {
            throw new BadRequestAlertException("A new farm cannot already have an Id");
        }
        if (!userRepository.existsById(farmDto.getUser().getId())) {
            throw new BadRequestAlertException("user ID does not exist");
        }
        FarmDto farmDto1 = farmService.addFarm(farmDto);
        return new ResponseEntity<>(farmDto1,HttpStatus.CREATED);

    }

    @GetMapping("/farm")
    public ResponseEntity<List<FarmDto>> getAllFarms(FarmDto farmDto) {
        List<FarmDto> allFarms = farmService.getAllfarms(farmDto);

        if (allFarms.isEmpty()) {
            throw new BadRequestAlertException("No Farm are present in the database");
        }
        return new ResponseEntity<>(allFarms, HttpStatus.OK);
    }

    @GetMapping("/farm/{id}")
    public ResponseEntity<Optional<FarmDto>> getFarmById(@PathVariable Long id) {
        if(!farmRepository.existsById(id)){
            throw new BadRequestAlertException("farm not found");
        }
        Optional<FarmDto> getFarm = farmService.getFarmById(id);
        return new ResponseEntity<>(getFarm, HttpStatus.OK);
    }


    @PutMapping("/farm/{id}")
    public ResponseEntity<FarmDto> updateFarm(@PathVariable Long id, @RequestBody FarmDto farmDto) {
        if (farmDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }
        if (!farmRepository.existsById(id)) {
            throw new BadRequestAlertException("farm not found ");
        }
        if (!userRepository.existsById(farmDto.getUser().getId())) {
            throw new BadRequestAlertException("user ID does not exist");
        }

        FarmDto result = farmService.updateFarm(farmDto);
        return ResponseEntity.ok().body(result);

    }

    @PatchMapping("/farm/{id}")
    public ResponseEntity<Optional<FarmDto>> partialUpdateFarm(@PathVariable Long id, @RequestBody FarmDto farmDto) {
        if (farmDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }
        if (!farmRepository.existsById(id)) {
            throw new BadRequestAlertException("farm not found");
        }
        if (!userRepository.existsById(farmDto.getUser().getId())) {
            throw new BadRequestAlertException("user ID does not exist");
        }
        Optional<FarmDto> result1 = farmService.partialUpdate(farmDto);
        return ResponseEntity.ok().body(result1);
    }

    @DeleteMapping("/farm/{id}")
    public ResponseEntity<String> deleteFarm(@PathVariable Long id) {
        try {
            farmService.checkIfFarmDeletable(id); // Method to check if farm is deletable
            farmService.deleteFarmById(id);
            String successMessage = "Farm with ID " + id + " has been deleted successfully.";
            return ResponseEntity.ok(successMessage);
        } catch (EmptyResultDataAccessException ex) {

            throw new BadRequestAlertException("Farm not found.");

        } catch (DataAccessException ex) {
            throw new BadRequestAlertException("cannot delete farm");
        }
    }

}
