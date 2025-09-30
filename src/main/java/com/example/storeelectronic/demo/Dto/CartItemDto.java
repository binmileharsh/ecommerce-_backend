package com.example.storeelectronic.demo.Dto;

import com.example.storeelectronic.demo.entities.Cart;
import com.example.storeelectronic.demo.entities.CartItem;
import com.example.storeelectronic.demo.entities.Product;
import com.example.storeelectronic.demo.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDto {

    private int cartItemId;

    private ProductDto product;
    private int quantity;
    private int totalPrice;


}
