package com.example.Farm_management.repository;

import com.example.Farm_management.domain.Plot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlotRepository extends CrudRepository<Plot, Long> {
    boolean existsById(Plot plot);
}

