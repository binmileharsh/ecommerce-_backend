package com.example.storeelectronic.demo.Dto;

import com.example.storeelectronic.demo.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Createorderrequest {

    private String orderStatus="PENDING";
    private String Payment_status="NOT PAID";
    private int order_amt;

    private String billing_Address;
    private String phone;
    private String billing_Name;
    private Date order_Date=new Date();



    private User user;


    private List<OrderItemDto> orderItems=new ArrayList<>();
}
