package com.example.ecommerce.repository;

import com.example.ecommerce.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    ShoppingCart findShoppingCartByUser_Id(Integer id);
}
