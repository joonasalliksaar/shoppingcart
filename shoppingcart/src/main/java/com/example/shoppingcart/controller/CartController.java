package com.example.shoppingcart.controller;

import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add-product")
    public String addProduct(@RequestBody Product product) {
        return cartService.addProduct(product);
    }

    @GetMapping("/get-all-products")
    public List<Product> getAllProducts() {
        return cartService.getAllProducts();
    }

    @DeleteMapping("/delete-product-by-name/{name}")
    public String deleteProductByName(@PathVariable String name) {
        return cartService.deleteProductByName(name);
    }

    @GetMapping("/cart-total")
    public Double calculateCartTotal() {
        return cartService.calculateCartTotal();
    }

    @GetMapping("/cart-total-with-discount")
    public Double discountTenPercent() {
        return cartService.discountTenPercent();
    }
}
