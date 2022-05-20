package com.example.ecommerce.repository;

import com.example.ecommerce.entity.OrderDetail;
import com.example.ecommerce.entity.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {

}
