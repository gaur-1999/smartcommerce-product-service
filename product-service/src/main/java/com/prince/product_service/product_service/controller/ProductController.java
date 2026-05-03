package com.prince.product_service.product_service.controller;


import com.prince.product_service.product_service.dto.ProductRequest;
import com.prince.product_service.product_service.dto.ProductResponse;
import com.prince.product_service.product_service.entity.Product;
import com.prince.product_service.product_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private String getCurrentUserEmail() {
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        return authentication.getName();
    }

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(
            @Valid @RequestBody ProductRequest request
    ) {
        String userEmail = getCurrentUserEmail();

        ProductResponse response =
                productService.addProduct(request, userEmail);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(
                productService.getAllProducts()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                productService.getProductById(id)
        );
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponse>>
    getByCategory(
            @PathVariable Product.Category category
    ) {
        return ResponseEntity.ok(
                productService.getProductsByCategory(category)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(
            @RequestParam String name
    ) {
        return ResponseEntity.ok(
                productService.searchProducts(name)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request
    ) {
        String userEmail = getCurrentUserEmail();
        return ResponseEntity.ok(
                productService.updateProduct(
                        id, request, userEmail)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long id
    ) {
        String userEmail = getCurrentUserEmail();
        return ResponseEntity.ok(
                productService.deleteProduct(id, userEmail)
        );
    }
}
