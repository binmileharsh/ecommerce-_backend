package com.example.storeelectronic.demo.Dto;

import com.example.storeelectronic.demo.entities.Category;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDto {
    private String productId;
    private String title;
    private String description;
    private int price;
    private int quantity;
    private int discountedPrice;
    private Date addedDate;
    private boolean live;
    private boolean stock;
    private String imageName;
    private CategoryDto category;
}
