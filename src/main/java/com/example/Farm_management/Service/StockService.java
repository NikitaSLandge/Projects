package com.example.Farm_management.Service;

import com.example.Farm_management.Service.dto.StockDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StockService {

    StockDto save(StockDto stockDto);

    List<StockDto> getAllStocks();

    Optional<StockDto> getStock(Long id);

    StockDto update(StockDto stockDto);

    Optional<StockDto> partialUpdate(StockDto stockDto);

    void delete(Long id);
}
