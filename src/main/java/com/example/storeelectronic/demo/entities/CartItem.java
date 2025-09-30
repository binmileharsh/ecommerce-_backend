package com.example.storeelectronic.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartItemId;
    @OneToOne
    @JoinColumn(name="product_id")
    private Product product;
    private int quantity;
    private int totalPrice;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;


}
