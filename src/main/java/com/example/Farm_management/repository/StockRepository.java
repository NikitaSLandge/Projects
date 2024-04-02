package com.example.Farm_management.repository;

import com.example.Farm_management.domain.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<Stock,Long> {

    boolean existsByCropId(Long id);

    void deleteByCropId(Long id);
}
