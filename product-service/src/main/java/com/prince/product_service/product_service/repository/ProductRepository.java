package com.prince.product_service.product_service.repository;




import com.prince.product_service.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    List<Product> findByCategory(Product.Category category);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCreatedBy(String email);
}
