package com.example.Farm_management.Service.Impl;

import com.example.Farm_management.Service.ProductService;
import com.example.Farm_management.Service.dto.ProductDto;
import com.example.Farm_management.Service.mapper.CropMapper;
import com.example.Farm_management.Service.mapper.ProductMapper;
import com.example.Farm_management.domain.Crop;
import com.example.Farm_management.domain.Product;
import com.example.Farm_management.repository.CropRepository;
import com.example.Farm_management.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();
        return productMapper.toDto(products);
    }

    @Override
    public Optional<ProductDto> getProducts(Long id) {
        return productRepository.findById(id).map(productMapper::toDto);
    }

    @Override
    public ProductDto update(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<ProductDto> partialUpdate(ProductDto productDto) {
        return productRepository.findById(productDto.getId()).map(existingProducts -> {
            productMapper.partialUpdate(existingProducts, productDto);
            return existingProducts;
        }).map(productRepository::save).map(productMapper::toDto);
    }


}
