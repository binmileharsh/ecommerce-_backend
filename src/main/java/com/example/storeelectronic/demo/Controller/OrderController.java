package com.example.storeelectronic.demo.Controller;

import com.example.storeelectronic.demo.Dto.Createorderrequest;
import com.example.storeelectronic.demo.Dto.OrderDto;
import com.example.storeelectronic.demo.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ordersave")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{userid}/{cartid}/saveorder")
    @PreAuthorize("hasRole('NORMAL')")
    public ResponseEntity<OrderDto> createOrder(
            @RequestBody Createorderrequest orderDto,
            @PathVariable String userid,
            @PathVariable String cartid) {

        OrderDto savedOrder = orderService.createorder(orderDto, userid, cartid);
        return ResponseEntity.ok(savedOrder);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{orderid}")
    public ResponseEntity<String> deleteOrder(

            @PathVariable String orderid) {

        String msg = orderService.orderremove(orderid);
        return ResponseEntity.ok(msg);
    }
}
