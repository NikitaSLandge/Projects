package com.example.Farm_management.Service.mapper;

import org.mapstruct.*;

import java.util.List;

public interface Entitymapper<D,E> {

    /**
     * Contract for a generic dto to entity mapper.
     *
     * @param "D" - DTO type parameter.
     * @param "E" - Entity type parameter.
     */

        E toEntity(D dto);

        D toDto(E entity);

        List<E> toEntity(List<D> dtoList);

        List<D> toDto(List<E> entityList);

        @Named("partialUpdate")
        @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        void partialUpdate(@MappingTarget E entity, D dto);
    }


