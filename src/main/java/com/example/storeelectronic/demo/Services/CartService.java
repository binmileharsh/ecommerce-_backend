package com.example.storeelectronic.demo.Services;

import com.example.storeelectronic.demo.Dto.AddItemDto;
import com.example.storeelectronic.demo.Dto.CartDto;
import com.example.storeelectronic.demo.Dto.CartItemDto;
import lombok.Data;
import org.springframework.stereotype.Service;
@Service
public interface CartService {
    CartDto additemtocart(String userid, AddItemDto request);
    void removeitemfromcart(String userid,int cartitemid);
    void clearCart(String userid);
    CartDto getCart(String Userid);


}
