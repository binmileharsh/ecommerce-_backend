package com.example.storeelectronic.demo.Services;

import com.example.storeelectronic.demo.Dto.Createorderrequest;
import com.example.storeelectronic.demo.Dto.OrderDto;
import com.example.storeelectronic.demo.entities.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    OrderDto  createorder(Createorderrequest orderdto , String userid, String cartid);
    String orderremove(String orderid);
    List<Orders> allorder(String userid);
}
