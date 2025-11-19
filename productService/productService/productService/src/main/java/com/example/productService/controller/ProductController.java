package com.example.productService.controller;

import com.example.productService.model.Product;
import com.example.productService.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // GET /products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    // GET /products/{productId}
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        try {
            Product product = productRepository.findById(productId);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /products
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        try {
            int result = productRepository.save(product);
            if (result > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create product");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // PUT /products/{productId}
    @PutMapping("/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        try {
            int result = productRepository.update(productId, product);
            if (result > 0) {
                return ResponseEntity.ok("Product updated successfully");
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // DELETE /products/{productId}
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        try {
            int result = productRepository.deleteById(productId);
            if (result > 0) {
                return ResponseEntity.ok("Product deleted successfully");
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}

