package com.example.Farm_management.web.rest;

import com.example.Farm_management.Service.StockService;
import com.example.Farm_management.Service.dto.StockDto;
import com.example.Farm_management.repository.CropRepository;
import com.example.Farm_management.repository.FarmRepository;
import com.example.Farm_management.repository.StockRepository;
import com.example.Farm_management.web.exception.BadRequestAlertException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("api")
public class StockResource {
    private final StockService stockService;
    private final StockRepository stockRepository;
    private final FarmRepository farmRepository;
    private final CropRepository cropRepository;

    public StockResource(StockService stockService, StockRepository stockRepository, FarmRepository farmRepository, CropRepository cropRepository) {
        this.stockService = stockService;
        this.stockRepository = stockRepository;
        this.farmRepository = farmRepository;
        this.cropRepository = cropRepository;
    }

    @PostMapping("/stock")
    public ResponseEntity<StockDto> createStock(@RequestBody StockDto stockDto) {
        if (stockDto.getId() != null) {
            throw new BadRequestAlertException("A new stock cannot already have an ID");
        }
        if (!cropRepository.existsById(stockDto.getCrop().getId())) {
            throw new BadRequestAlertException("Crop ID does not exist");
        }
        if (stockRepository.existsByCropId(stockDto.getCrop().getId())) {
            throw new BadRequestAlertException("Crop already exist");
        }
        StockDto result = stockService.save(stockDto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/stock")
    public ResponseEntity<List<StockDto>> getAllStocks() {

        List<StockDto> stockList = stockService.getAllStocks();
        if (stockList.isEmpty()) {
            throw new BadRequestAlertException("No stock is present in the database");
        }
        return ResponseEntity.ok().body(stockList);
    }

    @GetMapping("/stock/{id}")
    public ResponseEntity<Optional<StockDto>> getStock(@PathVariable("id") Long id) {
        if(!stockRepository.existsById(id)){
            throw new BadRequestAlertException("Stock not found");
        }
        Optional<StockDto> stockDto = stockService.getStock(id);
        return ResponseEntity.ok().body(stockDto);
    }

    @PutMapping("/stock/{id}")
    public ResponseEntity<StockDto> updateStocks(@PathVariable Long id, @RequestBody StockDto stockDto) {
        if (stockDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }

        if (!stockRepository.existsById(id)) {
            throw new BadRequestAlertException("Stock not found");
        }

        StockDto result = stockService.update(stockDto);
        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/stock/{id}")
    public ResponseEntity<Optional<StockDto>> partialUpdateStocks(
            @PathVariable Long id, @RequestBody StockDto stockDto) {
        if (stockDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }
        if (!stockRepository.existsById(id)) {
            throw new BadRequestAlertException("Stock not found");
        }
        Optional<StockDto> result = stockService.partialUpdate(stockDto);
        return ResponseEntity.ok().body(result);
    }


    @DeleteMapping("/stock/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable("id") Long id) {
        if(!stockRepository.existsById(id)){
            throw new BadRequestAlertException("Stock not found");
        }
        stockService.delete(id);
        String successMessage = "Stock with ID " + id + " has been deleted successfully.";
        return ResponseEntity.ok(successMessage);
    }
}
