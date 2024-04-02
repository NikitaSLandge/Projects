package com.example.Farm_management.web.rest;

import com.example.Farm_management.Service.CropService;
import com.example.Farm_management.Service.dto.CropDto;
import com.example.Farm_management.repository.CropRepository;
import com.example.Farm_management.repository.FarmRepository;
import com.example.Farm_management.repository.PlotRepository;
import com.example.Farm_management.web.exception.BadRequestAlertException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CropResource {

    private final CropService cropService;
    private final CropRepository cropRepository;
    private final PlotRepository plotRepository;
    private final FarmRepository farmRepository;

    public CropResource(CropService cropService, CropRepository cropRepository, PlotRepository plotRepository, FarmRepository farmRepository) {
        this.cropService = cropService;
        this.cropRepository = cropRepository;
        this.plotRepository = plotRepository;
        this.farmRepository = farmRepository;
    }

    @PostMapping("/crop")
    public ResponseEntity<CropDto> createCrops(@RequestBody CropDto cropDto) {
        if (cropDto.getId() != null) {
            throw new BadRequestAlertException("A new crops cannot already have an ID");
        }
        if (!farmRepository.existsById(cropDto.getFarm().getId())) {
            throw new BadRequestAlertException("Farm ID does not exist");
        }
        if (!plotRepository.existsById(cropDto.getPlot().getId())) {
            throw new BadRequestAlertException("plot id does not exists");
        }
        if (plotRepository.existsById(cropDto.getPlot().getId())) {
            throw new BadRequestAlertException("crop for this plot already exists");
        }
        CropDto result = cropService.save(cropDto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/crop")
    public ResponseEntity<List<CropDto>> getAllCrops() {
        List<CropDto> allCrops = cropService.getAllCrops();
        if (allCrops.isEmpty()) {
            throw new BadRequestAlertException("No crop are present in the database");
        }
        return ResponseEntity.ok().body(allCrops);
    }

    @GetMapping("/crops")
    public ResponseEntity <List<CropDto>> getCropByName(@RequestParam String name){
        List<CropDto> crops= (List<CropDto>) cropService.getCropByName(name);
        return ResponseEntity.ok().body(crops);
    }

    @GetMapping("/crop/{id}")
    public ResponseEntity<Optional<CropDto>> getCrop(@PathVariable("id") Long id) {
        if (!cropRepository.existsById(id)) {
            throw new BadRequestAlertException("Crop not found");
        }
        Optional<CropDto> cropsDto = cropService.findOne(id);
        return ResponseEntity.ok().body(cropsDto);
    }

    @PutMapping("/crop/{id}")
    public ResponseEntity<CropDto> updateCrops(@PathVariable Long id, @RequestBody CropDto cropDto) throws URISyntaxException {
        if (cropDto.getId()==null) {
            throw new BadRequestAlertException("Invalid id");
        }
        if (!cropRepository.existsById(id)) {
            throw new BadRequestAlertException("Crop not found");
        }
        CropDto result = cropService.update(cropDto);
        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/crop/{id}")
    public ResponseEntity<Optional<CropDto>> partialUpdateCrops(
            @PathVariable Long id, @RequestBody CropDto cropDto) {
        if (cropDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }
        if (!cropRepository.existsById(id)) {
            throw new BadRequestAlertException("Crop not found");
        }
        Optional<CropDto> result = cropService.partialUpdate(cropDto);
        return ResponseEntity.ok().body(result);
    }


    @DeleteMapping("/crop/{id}")
    public ResponseEntity<String> deleteCrops(@PathVariable("id") Long id) {
        if (!cropRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found");
        }
        cropService.delete(id);
        String successMessage = "Crop with ID " + id + " has been deleted successfully.Associated stock has also been deleted.";
        return ResponseEntity.ok(successMessage);
    }

}
