package com.example.ecommerce.service;

import com.example.ecommerce.EcommerceApplication;
import com.example.ecommerce.config.H2JpaConfig;
import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.ShoppingCart;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.ShoppingCartRepository;
import com.example.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EcommerceApplication.class, H2JpaConfig.class})
@ActiveProfiles("test")
class CartServiceTest {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @BeforeEach
    void save(){
        Product product = new Product();
        product.setId(1);
        product.setPrice(new BigDecimal(300000));
        product.setDetail("Detail");
        product.setName("Product 1");
        productRepository.save(product);
        User user = new User();
        user.setId(1);
        user.setUserName("Test User");
        userRepository.save(user);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        Set<CartItem> cartItems = new HashSet<>();
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setShoppingCart(shoppingCart);
        cartItem.setQuantity(3);
        cartItem.setTotal(new BigDecimal(3).multiply(product.getPrice()));
        cartItems.add(cartItem);
//        CartItem a = cartItemRepository.save(cartItem);
        shoppingCart.setCartItems(cartItems);
        BigDecimal total = new BigDecimal(0);
        for (CartItem cartItem1:
                cartItems) {
            total = total.add(cartItem1.getTotal());
        }
        shoppingCart.setTotal(total);
        System.out.println((long) shoppingCartRepository.save(shoppingCart).getCartItems().size());
        System.out.println(cartItemRepository.save(cartItem));
    }
    @Test
    void findShoppingCartByUserId() {
        System.out.println(shoppingCartRepository.findShoppingCartByUser_Id(1));
    }

}