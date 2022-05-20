package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findAll(Pageable pageable);
}
