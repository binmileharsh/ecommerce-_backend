package com.example.storeelectronic.demo.Dto;

import com.example.storeelectronic.demo.entities.Orders;
import com.example.storeelectronic.demo.entities.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {


    private String OrderItemId;
    private int quantity;
    private ProductDto productdto;
    private int total_Price;

}
