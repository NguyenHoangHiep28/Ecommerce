package com.example.ecommerce.controller;

import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.CartItemId;
import com.example.ecommerce.entity.ShoppingCart;
import com.example.ecommerce.entity.dtos.request.CartItemRequest;
import com.example.ecommerce.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping("api/v1/carts")
public class CartApi {
    private final CartService cartService;

    public CartApi(CartService cartService) {
        this.cartService = cartService;
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getUserCart(@RequestParam(name = "userId") int userId) {
        ShoppingCart shoppingCart = cartService.findShoppingCartByUserId(userId);
        if (shoppingCart != null){
            return ResponseEntity.ok(shoppingCart);
        }
        return ResponseEntity.notFound().build();
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addItemToCart(@RequestBody CartItemRequest cartItemRequest){
        return ResponseEntity.ok(cartService.saveCartItem(cartItemRequest));
    }
    @RequestMapping(method = RequestMethod.POST, path = "delete")
    public ResponseEntity<?> removeCartItem(@RequestBody CartItemId cartItemId){
        CartItem returnResult = cartService.removeCartItem(cartItemId);
        if (returnResult != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
