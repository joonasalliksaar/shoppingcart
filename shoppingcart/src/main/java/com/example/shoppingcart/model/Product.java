package com.example.shoppingcart.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "product")
@ToString

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String membership;
    private String name;
    private Double price;
    private Integer quantity;

    public double getProductsPrice() {
        return price*quantity;
    }
}