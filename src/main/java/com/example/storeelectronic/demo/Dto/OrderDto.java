package com.example.storeelectronic.demo.Dto;

import com.example.storeelectronic.demo.entities.OrderItem;
import com.example.storeelectronic.demo.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String orderId;
    private String orderStatus="PENDING";
private String Payment_status="NOT PAID";
    private int order_amt;

    private String billing_Address;
    private String phone;
    private String billing_Name;
    private Date order_Date=new Date();
    private Date delivered_Date;


    private User user;


    private List<OrderItemDto> orderItems=new ArrayList<>();

}
