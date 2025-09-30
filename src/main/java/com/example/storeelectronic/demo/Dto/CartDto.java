package com.example.storeelectronic.demo.Dto;

import com.example.storeelectronic.demo.entities.CartItem;
import com.example.storeelectronic.demo.entities.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {

    private String cartId;
    private Date createdate;

    private UserDto user;

    private List<CartItemDto> cartItems=new ArrayList<>();
}
