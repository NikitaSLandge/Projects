package com.example.Farm_management.repository;

import com.example.Farm_management.domain.Farm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmRepository extends CrudRepository<Farm, Long> {


    Farm findAllById(Long id);

    boolean existsById(Farm farm);

}
