package com.example.Farm_management.web.rest;

import com.example.Farm_management.Service.SupplierService;
import com.example.Farm_management.Service.dto.FarmDto;
import com.example.Farm_management.Service.dto.SupplierDto;
import com.example.Farm_management.repository.FarmRepository;
import com.example.Farm_management.repository.SupplierRepository;
import com.example.Farm_management.web.exception.BadRequestAlertException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class SupplierResource {

    private final SupplierService supplierService;
    private final SupplierRepository supplierRepository;
    private final FarmRepository farmRepository;

    public SupplierResource(SupplierService supplierService, SupplierRepository supplierRepository, FarmRepository farmRepository) {
        this.supplierService = supplierService;
        this.supplierRepository = supplierRepository;
        this.farmRepository = farmRepository;
    }

    @PostMapping("/supplier")
    public ResponseEntity<SupplierDto> createSuppliers(@RequestBody SupplierDto supplierDto) {
        if (supplierDto.getId() != null) {
            throw new BadRequestAlertException("A new Supplier cannot already have an Id");
        }
        if (!farmRepository.existsById(supplierDto.getFarm().getId())) {
            throw new BadRequestAlertException("Farm ID does not exist");
        }
        SupplierDto result = supplierService.save(supplierDto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/supplier")
    public ResponseEntity<List<SupplierDto>> getAllSuppliers() {
        List<SupplierDto> allSuppliers = supplierService.getAllSuppliers();
        if (allSuppliers.isEmpty()) {
            throw new BadRequestAlertException("no suppliers are present in database");
        }
        return ResponseEntity.ok().body(allSuppliers);
    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<Optional<SupplierDto>> getSupplier(@PathVariable Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new BadRequestAlertException("supplier not found");
        }
        Optional<SupplierDto> supplier = supplierService.getSupplier(id);
        return ResponseEntity.ok().body(supplier);
    }

    @PutMapping("/supplier/{id}")
    public ResponseEntity<SupplierDto> updateSupplier(@PathVariable Long id, @RequestBody SupplierDto supplierDto) {
        if (supplierDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }
        if (!supplierRepository.existsById(id)) {
            throw new BadRequestAlertException("supplier not found");
        }
        if (!farmRepository.existsById(supplierDto.getFarm().getId())) {
            throw new BadRequestAlertException("Farm ID does not exist");
        }
        SupplierDto result = supplierService.update(supplierDto);
        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/supplier/{id}")
    public ResponseEntity<Optional<SupplierDto>> partialUpdateSupplier(@PathVariable Long id, @RequestBody SupplierDto supplierDto) {
        if (supplierDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }
        if (!supplierRepository.existsById(id)) {
            throw new BadRequestAlertException("supplier not found");
        }
        if (!farmRepository.existsById(supplierDto.getFarm().getId())) {
            throw new BadRequestAlertException("Farm ID does not exist");
        }
        Optional<SupplierDto> result1 = supplierService.partialUpdate(supplierDto);
        return ResponseEntity.ok().body(result1);
    }

    @DeleteMapping("/supplier/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable("id") Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new BadRequestAlertException("supplier not found");
        }
        supplierService.delete(id);
        String successMessage = "supplier with ID " + id + " has been deleted successfully.";
        return ResponseEntity.ok(successMessage);
    }
}
