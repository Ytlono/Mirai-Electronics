package com.example.MiraiElectronics.controller;

import com.example.MiraiElectronics.service.cart.CartItemService;
import com.example.MiraiElectronics.service.cart.CartService;
import com.example.MiraiElectronics.service.auth.CustomUserDetails;
import com.example.MiraiElectronics.service.catalog.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController extends BaseController{
    private final CartService cartService;
    private final ProductService productService;
    private final CartItemService cartItemService;

    public CartController(CartService cartService, ProductService productService, CartItemService cartItemService) {
        this.cartService = cartService;
        this.productService = productService;
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public ResponseEntity<?> getCart(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(
                cartService.getCartByUser(
                        getFullUserOrThrow(userDetails))
        );
    }

    @GetMapping("/items")
    public ResponseEntity<?> getCartItem(@RequestParam Long itemId,
                                         @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(
                cartItemService.getById(
                        itemId,getFullUserOrThrow(userDetails))
        );
    }

    @PostMapping("/items")
    public ResponseEntity<?> addToCart(@RequestParam Long productId,
                                       @RequestParam(defaultValue = "1") int quantity,
                                       @AuthenticationPrincipal CustomUserDetails userDetails) {
        cartService.addItem(
                productService.findById(productId),
                quantity,
                getFullUserOrThrow(userDetails)
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Товар добавлен в корзину"));
    }

    @DeleteMapping("/items")
    public ResponseEntity<?> removeFromCart(@RequestParam Long itemId,
                                            @AuthenticationPrincipal CustomUserDetails userDetails) {
        cartItemService.removeItem(itemId, getFullUserOrThrow(userDetails));
        return ResponseEntity.ok(Map.of("message", "Товар удален из корзины"));
    }

    @PutMapping("/items")
    public ResponseEntity<?> updateCartItemQuantity(@RequestParam Long itemId,
                                                    @RequestParam(defaultValue = "1") int delta,
                                                    @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(
                cartItemService.updateQuantity(
                        itemId, delta, getFullUserOrThrow(userDetails))
        );
    }
}