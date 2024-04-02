package com.example.Farm_management.Service.Impl;

import com.example.Farm_management.Service.StockService;
import com.example.Farm_management.Service.dto.StockDto;
import com.example.Farm_management.Service.mapper.StockMapper;
import com.example.Farm_management.domain.Crop;
import com.example.Farm_management.domain.Stock;
import com.example.Farm_management.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public StockServiceImpl(StockRepository stockRepository, StockMapper stockMapper) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }

    @Override
    public StockDto save(StockDto stockDto) {
        Stock stock = stockMapper.toEntity(stockDto);
        stock = stockRepository.save(stock);
        return stockMapper.toDto(stock);
    }

    @Override
    public List<StockDto> getAllStocks() {
        List<Stock> stocks = (List<Stock>) stockRepository.findAll();
        return stockMapper.toDto(stocks);
    }

    @Override
    public Optional<StockDto> getStock(Long id) {
        return stockRepository.findById(id).map(stockMapper::toDto);
    }

    @Override
    public StockDto update(StockDto stockDto) {
        Stock stock = stockMapper.toEntity(stockDto);
        stock = stockRepository.save(stock);
        return stockMapper.toDto(stock);
    }

    @Override
    public Optional<StockDto> partialUpdate(StockDto stockDto) {
        return stockRepository.findById(stockDto.getId()).map(existingStocks -> {
            stockMapper.partialUpdate(existingStocks, stockDto);
            return existingStocks;
        }).map(stockRepository::save).map(stockMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        stockRepository.deleteById(id);
    }
}
