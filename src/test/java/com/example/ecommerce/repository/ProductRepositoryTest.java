package com.example.ecommerce.repository;

import com.example.ecommerce.EcommerceApplication;
import com.example.ecommerce.config.H2JpaConfig;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.enums.ProductSimpleStatus;
import com.example.ecommerce.util.StringHelper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EcommerceApplication.class, H2JpaConfig.class})
@ActiveProfiles("test")
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;
    Faker faker = new Faker();
    @Test
    public void createProducts() {
        boolean nameExisting = false;
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            String productName = faker.food().dish();

            for (Product product :
                    products) {
                if (product.getName().equals(productName)) {
                    nameExisting = true;
                    break;
                }
            }
            if (nameExisting) {
                i--;
                nameExisting = false;
                continue;
            }
            String slug = StringHelper.toSlug(productName);
            String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
            String detail = "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
            BigDecimal price = new BigDecimal(faker.number().randomNumber(5, true));
            ProductSimpleStatus status = ProductSimpleStatus.ACTIVE;
            Product product = new Product();
            product.setName(productName);
            product.setSlug(slug);
            product.setDescription(description);
            product.setThumbnails(slug + "-image1.png");
            product.setPrice(price);
            product.setStatus(status);
            product.setDetail(detail);
            products.add(product);
        }
        productRepository.saveAll(products);
        productRepository.findAll().forEach(product -> {
            System.out.println(product.toString());
        });
    }
}