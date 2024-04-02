package com.example.Farm_management.Service;

import com.example.Farm_management.Service.dto.PlotDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PlotService {

    PlotDto addPlot(PlotDto plotDto);

    List<PlotDto> findPlot();


    Optional<PlotDto> findById(Long id);

    void deleteplot(Long id);


    PlotDto updatePlot(PlotDto plotDto);

    Optional<PlotDto> partialUpdate(PlotDto plotDto);

    void checkIfPlotDeletable(Long id) ;
}
