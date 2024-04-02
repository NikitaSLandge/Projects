package com.example.Farm_management.repository;

import com.example.Farm_management.domain.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {


}
