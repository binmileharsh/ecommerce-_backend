package com.example.storeelectronic.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id

    private String OrderItemId;
    private int quantity;
    private int total_Price;
    @ManyToOne
    private Product product;
    @ManyToOne
    @JsonBackReference
    private Orders order;

}
