package com.example.ecommerce.controller;
import com.example.ecommerce.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin()
@RestController
@RequestMapping(path = "api/v1/products")
public class ProductApi {

    private ProductService productService;

    public ProductApi(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit) {
        return ResponseEntity.ok(productService.getAll(PageRequest.of(page - 1, limit)));
    }
}
