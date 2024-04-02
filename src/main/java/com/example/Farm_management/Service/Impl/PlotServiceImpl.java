package com.example.Farm_management.Service.Impl;

import com.example.Farm_management.domain.Plot;
import com.example.Farm_management.repository.PlotRepository;
import com.example.Farm_management.Service.PlotService;
import com.example.Farm_management.Service.mapper.PlotMapper;
import com.example.Farm_management.Service.dto.PlotDto;
import com.example.Farm_management.web.exception.BadRequestAlertException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlotServiceImpl implements PlotService {

    private final PlotRepository plotRepository;
    private final PlotMapper plotMapper;

    public PlotServiceImpl(PlotRepository plotRepository, PlotMapper plotMapper) {
        this.plotRepository = plotRepository;
        this.plotMapper = plotMapper;
    }


    @Override
    public PlotDto addPlot(PlotDto plotDto) {
        Plot plot = plotMapper.toEntity(plotDto);
        Plot savedplot = plotRepository.save(plot);
        return plotMapper.toDto(savedplot);
    }

    @Override
    public List<PlotDto> findPlot() {
        //Plot plot = plotMapper.toEntity(plotDto);
        List<Plot> savedplot = (List<Plot>) plotRepository.findAll();
        return plotMapper.toDto(savedplot);

    }

    @Override
    public Optional<PlotDto> findById(Long id) {
        return plotRepository.findById(id).map(plotMapper::toDto);
    }


    @Override
    public PlotDto updatePlot(PlotDto plotDto) {
        Plot plot = plotMapper.toEntity(plotDto);
        Optional<Plot> existingPlot = plotRepository.findById(plotDto.getId());
        plot = plotRepository.save(plot);
        return plotMapper.toDto(plot);
    }

    @Override
    public Optional<PlotDto> partialUpdate(PlotDto plotDto) {
        return plotRepository.findById(plotDto.getId()).map(existingPlot -> {
            plotMapper.partialUpdate(existingPlot, plotDto);
            return existingPlot;
        }).map(plotRepository::save).map(plotMapper::toDto);
    }

    @Override
    public void checkIfPlotDeletable(Long id) {
        Plot plot = plotRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        if (plot.getCrop() != null) {
            throw new BadRequestAlertException("Cannot delete plot with associated crops.");
        }
    }

    @Override
    public void deleteplot(Long id) {
        plotRepository.deleteById(id);
    }


}

