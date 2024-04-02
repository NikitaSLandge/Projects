package com.example.Farm_management.web.rest;

import com.example.Farm_management.Service.ProductService;
import com.example.Farm_management.Service.dto.ProductDto;
import com.example.Farm_management.repository.ProductRepository;
import com.example.Farm_management.web.exception.BadRequestAlertException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api")
public class ProductResource {
    private final ProductService productService;

    private final ProductRepository productRepository;

    public ProductResource(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        if (productDto.getId() != null) {
            throw new BadRequestAlertException("A new product cannot already have an id");
        }
        ProductDto result = productService.save(productDto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> allProducts = productService.getAllProducts();
        if(allProducts.isEmpty()){
            throw new BadRequestAlertException("No product is present in database");
        }
        return ResponseEntity.ok().body(allProducts);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Optional<ProductDto>> getProduct(@PathVariable Long id) {
        if(!productRepository.existsById(id)){
            throw new BadRequestAlertException("Product not found");
        }
        Optional<ProductDto> productDto1 = productService.getProducts(id);
        return ResponseEntity.ok().body(productDto1);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        if (productDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }

        if (!productRepository.existsById(id)) {
            throw new BadRequestAlertException("Product not found");
        }

        ProductDto result = productService.update(productDto);
        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<Optional<ProductDto>> partialUpdateProducts(
            @PathVariable Long id, @RequestBody ProductDto productDto) {
        if (productDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }

        if (!productRepository.existsById(id)) {
            throw new BadRequestAlertException("Product not found");
        }

        Optional<ProductDto> result = productService.partialUpdate(productDto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        if(!productRepository.existsById(id)){
            throw new BadRequestAlertException("Product not found");
        }
        productService.delete(id);
        String successMessage = "Product with ID " + id + " has been deleted successfully.";
        return ResponseEntity.ok(successMessage);
    }
}
