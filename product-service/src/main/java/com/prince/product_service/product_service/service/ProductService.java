package com.prince.product_service.product_service.service;


import com.prince.product_service.product_service.dto.ProductRequest;
import com.prince.product_service.product_service.dto.ProductResponse;
import com.prince.product_service.product_service.entity.Product;
import com.prince.product_service.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public ProductResponse addProduct(
            ProductRequest request,
            String createdBy
    ) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .category(request.getCategory())
                .createdBy(createdBy)
                .build();

        Product savedProduct = productRepository.save(product);
        return ProductResponse.fromEntity(savedProduct);
    }
    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll()

                .stream()
                .map(ProductResponse::fromEntity)
                .collect(Collectors.toList());
    }
    public ProductResponse getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found with id: " + id)
                );

        return ProductResponse.fromEntity(product);
    }
    public List<ProductResponse> getProductsByCategory(
            Product.Category category) {

        return productRepository.findByCategory(category)

                .stream()
                .map(ProductResponse::fromEntity)
                .collect(Collectors.toList());

    }
    public List<ProductResponse> searchProducts(String name) {

        return productRepository
                .findByNameContainingIgnoreCase(name)
                .stream()
                .map(ProductResponse::fromEntity)
                .collect(Collectors.toList());
    }
    public ProductResponse updateProduct(
            Long id,
            ProductRequest request,
            String userEmail
    ) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found with id: " + id)
                );
        if (!product.getCreatedBy().equals(userEmail)) {
            throw new RuntimeException(
                    "You can only update your own products"
            );
        }
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(request.getCategory());
        Product updatedProduct = productRepository.save(product);
        return ProductResponse.fromEntity(updatedProduct);
    }
    public String deleteProduct(Long id, String userEmail) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found with id: " + id)
                );

        if (!product.getCreatedBy().equals(userEmail)) {
            throw new RuntimeException(
                    "You can only delete your own products"
            );
        }

        productRepository.deleteById(id);
        return "Product deleted successfully";
    }
}
