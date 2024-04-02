package com.example.Farm_management.repository;

import com.example.Farm_management.domain.Crop;
import com.example.Farm_management.domain.Plot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropRepository extends CrudRepository<Crop, Long> {

    @Query("SELECT c FROM Crop c WHERE c.name LIKE %:name%")
    List<Crop> getCropByName(String name);

}
