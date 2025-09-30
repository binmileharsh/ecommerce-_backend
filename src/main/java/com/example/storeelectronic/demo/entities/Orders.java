package com.example.storeelectronic.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
@Id
    private String orderId;
    private String orderStatus;
    private String Payment_status;
    private int order_amt;
    @Column
    private String billing_Address;

    private String phone;
    private String billing_Name;
    private Date order_Date;
    private Date delivered_Date;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy="order", cascade=CascadeType.ALL, orphanRemoval=true)    private List<OrderItem> orderItems=new ArrayList<>();



}
