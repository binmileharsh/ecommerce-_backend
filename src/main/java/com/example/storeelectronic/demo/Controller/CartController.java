package com.example.storeelectronic.demo.Controller;

import com.example.storeelectronic.demo.Dto.AddItemDto;
import com.example.storeelectronic.demo.Dto.ApiResponse;
import com.example.storeelectronic.demo.Dto.CartDto;
import com.example.storeelectronic.demo.Dto.CartItemDto;
import com.example.storeelectronic.demo.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartservice;
    @PreAuthorize("hasAnyRole('ADMIN','NORMAL')")
    @PostMapping("/{userid}")
    public ResponseEntity<CartDto> additemtocart(@RequestBody AddItemDto request, @PathVariable String userid){
        CartDto cart=this.cartservice.additemtocart(userid,request);
        return new  ResponseEntity<>(cart, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN','NORMAL')")
    @DeleteMapping("{userid}/items/{itemid}")
    public ResponseEntity<ApiResponse<String>> removeitemfromcart(@PathVariable String userid, @PathVariable int itemid) {
        this.cartservice.removeitemfromcart(userid, itemid);

        ApiResponse<String> response = ApiResponse.<String>builder()
                .data("Item has been deleted")
                .success(true)
                .messsage("Item removed from cart successfully")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/clear/{userid}")
    public ResponseEntity<ApiResponse<String>> removeitemfromcart(@PathVariable String userid) {
        this.cartservice.clearCart(userid);

        ApiResponse<String> response = ApiResponse.<String>builder()
                .data("clear cart")
                .success(true)
                .messsage("Items removed from cart successfully")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN','NORMAL')")
@GetMapping("/{userid}")
public ResponseEntity<CartDto> getcartbyid(@PathVariable String userid){
        CartDto cart=this.cartservice.getCart(userid);
        return new ResponseEntity<>(cart,HttpStatus.OK);

}




}
