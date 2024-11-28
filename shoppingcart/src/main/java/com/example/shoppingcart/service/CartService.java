package com.example.shoppingcart.service;

import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CartService {

    private final ProductRepository productRepository;
    boolean isMember = true;
    private static double TAX_RATE = 22;

    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String addProduct(Product product) {
        productRepository.save(product);
        return "Product added successfully";
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public String deleteProductByName(String name) {
        productRepository.deleteByName(name); // Calls the derived delete query
        return "Product with name '" + name + "' was deleted.";
    }

    /*
    for (Product product : getAllProducts()) {
        if (product.getName().equals(name)) {
            productRepository.delete(product);
            return "Product deleted. List of products: " + getAllProducts();
        }
    }return "User not found. Current users: " + getAllProducts();
}

     */
    @Scheduled(fixedRate = 10000)
    public Double calculateCartTotal() {
        double cartTotal = 0;
        for (Product product : getAllProducts()) {
            cartTotal += product.getProductsPrice();
        }
        System.out.println("Scheduler total:" + (cartTotal * calculateTax()));
        return cartTotal*calculateTax();
    }

    public double calculateTax(){
        return 1 + TAX_RATE / 100;
    }

    public Double discountTenPercent() {
        if (isMember) {
            double discount = calculateCartTotal() * 0.1;
            double cartTotalAfterDiscount = calculateCartTotal() - discount;
            return cartTotalAfterDiscount;
        }
        return calculateCartTotal();
    }
}

