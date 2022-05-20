package com.example.ecommerce.repository;

import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {

}
