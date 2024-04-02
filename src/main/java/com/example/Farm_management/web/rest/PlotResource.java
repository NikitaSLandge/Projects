package com.example.Farm_management.web.rest;

import com.example.Farm_management.Service.PlotService;
import com.example.Farm_management.Service.dto.PlotDto;
import com.example.Farm_management.repository.FarmRepository;
import com.example.Farm_management.repository.PlotRepository;
import com.example.Farm_management.web.exception.BadRequestAlertException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PlotResource {

    private final PlotService plotService;
    private final PlotRepository plotRepository;
    private final FarmRepository farmRepository;

    public PlotResource(PlotService plotService, PlotRepository plotRepository, FarmRepository farmRepository) {
        this.plotService = plotService;
        this.plotRepository = plotRepository;
        this.farmRepository = farmRepository;
    }

    @PostMapping("/plot")
    public ResponseEntity<PlotDto> addPlot(@RequestBody PlotDto plotDto) {
        if (plotDto.getId() != null) {
            throw new BadRequestAlertException("A new plot cannot already have an id");
        }
        if (!farmRepository.existsById(plotDto.getFarm().getId())) {
            throw new BadRequestAlertException("Farm ID does not exist");
        }
        PlotDto savedPlots = plotService.addPlot(plotDto);
        return new ResponseEntity<>(savedPlots, HttpStatus.CREATED);
    }

    @GetMapping("/plot")
    public ResponseEntity<List<PlotDto>> findPlot() {
        List<PlotDto> plot = plotService.findPlot();
        if(plot.isEmpty()){
            throw new BadRequestAlertException("No plot present in database");
        }
        return new ResponseEntity<>(plot, HttpStatus.OK);
    }

    @GetMapping("plot/{id}")
    public ResponseEntity<Optional<PlotDto>> findById(@PathVariable Long id ) {
        if (!plotRepository.existsById(id)) {
            throw new BadRequestAlertException("plot not found");
        }
        Optional<PlotDto> plot = plotService.findById(id);
        return new ResponseEntity<>(plot, HttpStatus.OK);
    }


    @PutMapping("plot/{id}")
    public ResponseEntity<PlotDto> updatePlot(@PathVariable Long id, @RequestBody PlotDto plotDto) {
        if (plotDto.getId() == null) {
            throw new BadRequestAlertException("Id is null");
        }
        if (!plotRepository.existsById(id)) {
            throw new BadRequestAlertException("plot not found");
        }
        if (!farmRepository.existsById(plotDto.getFarm().getId())) {
            throw new BadRequestAlertException("Farm ID does not exist");
        }
        PlotDto plot = plotService.updatePlot(plotDto);
        return ResponseEntity.ok().body(plot);
    }

    @PatchMapping("/plot/{id}")
    public ResponseEntity<Optional<PlotDto>> partialUpdatePlot(@PathVariable Long id, @RequestBody PlotDto plotDto) {
        if (plotDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }

        if (!plotRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found");
        }
        if (!farmRepository.existsById(plotDto.getFarm().getId())) {
            throw new BadRequestAlertException("Farm ID does not exist");
        }
        Optional<PlotDto> result = plotService.partialUpdate(plotDto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("plot/{id}")
    public ResponseEntity<String> deletePlot(@PathVariable long id) {
        try {
            plotService.checkIfPlotDeletable(id); // Method to check if plot is deletable
            plotService.deleteplot(id);
            String successMessage = "Plot with ID " + id + " has been deleted successfully.";
            return ResponseEntity.ok(successMessage);

        } catch (EmptyResultDataAccessException ex) {
            throw new BadRequestAlertException("Plot not found.");

        } catch (DataAccessException ex) {
            throw new BadRequestAlertException("cannot delete plot");
        }
    }

}
