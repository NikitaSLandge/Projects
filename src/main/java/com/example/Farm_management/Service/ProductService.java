package com.example.Farm_management.Service;

import com.example.Farm_management.Service.dto.ProductDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    ProductDto save(ProductDto productDto);

    List<ProductDto> getAllProducts();

    Optional<ProductDto> getProducts(Long id);

    ProductDto update(ProductDto productDto);

    void delete(Long id);


    Optional<ProductDto> partialUpdate(ProductDto productDto);
}
