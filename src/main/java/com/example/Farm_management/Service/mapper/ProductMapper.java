package com.example.Farm_management.Service.mapper;

import com.example.Farm_management.domain.Product;
import com.example.Farm_management.Service.dto.ProductDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper extends Entitymapper<ProductDto, Product> {

    ProductDto toDto(Product product);

    List<ProductDto> toDto(List<Product> products);

    Product toEntity(ProductDto productDto);
}
