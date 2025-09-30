package com.example.storeelectronic.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    private String productId;
    private String title;
    @Column(length = 10000)
    private String description;
    private int price;
    private int quantity;
    private int discountedPrice;
    private Date addedDate;
    private boolean live;
    private boolean stock;
    private String imageName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    @JsonIgnore
    private  Category category;




}
