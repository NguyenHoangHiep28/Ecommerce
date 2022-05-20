package com.example.ecommerce.controller;

import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.specification.SearchBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping("api/v1/orders")
public class OrderApi {
    private final OrderService orderService;

    public OrderApi(OrderService orderService) {
        this.orderService = orderService;
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> getAllOrders(@RequestBody SearchBody search) {
        return ResponseEntity.ok(orderService.findAllAndSorting(search));
    }
}
