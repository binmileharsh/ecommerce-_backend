package com.example.storeelectronic.demo.Services.impl;

import com.example.storeelectronic.demo.Dto.Createorderrequest;
import com.example.storeelectronic.demo.Dto.OrderDto;
import com.example.storeelectronic.demo.Dto.OrderItemDto;
import com.example.storeelectronic.demo.Dto.ProductDto;
import com.example.storeelectronic.demo.Repository.Cartrepository;
import com.example.storeelectronic.demo.Repository.Orderrepo;
import com.example.storeelectronic.demo.Repository.UserRepository;
import com.example.storeelectronic.demo.Services.OrderService;
import com.example.storeelectronic.demo.entities.*;
import com.example.storeelectronic.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private Orderrepo orderrepo;

    @Autowired
    private Cartrepository cartrepo;

    @Autowired
    private UserRepository userrepo;

    @Override
    public OrderDto createorder(Createorderrequest orderdto, String userid, String cartid) {

        User founduser = this.userrepo.findById(userid)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userid));
        Cart foundcart = this.cartrepo.findById(cartid)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "id", cartid));

        List<CartItem> ALLCARTITEM = foundcart.getCartItems();
        if (ALLCARTITEM.isEmpty()) {
            throw new ResourceNotFoundException("no", "no item founds", false);
        }

        Orders order = Orders.builder()
                .orderId(UUID.randomUUID().toString())
                .order_amt(orderdto.getOrder_amt())
                .orderStatus(orderdto.getOrderStatus())
                .order_Date(orderdto.getOrder_Date())
                .user(founduser)
                .billing_Name(orderdto.getBilling_Name())
                .phone(orderdto.getPhone())
                .Payment_status(orderdto.getPayment_status())
                .billing_Address(orderdto.getBilling_Address())
                .delivered_Date(null)
                .build();

        AtomicReference<Integer> orderamount = new AtomicReference<>(0);
        List<OrderItem> orderItems = ALLCARTITEM.stream().map(cartitem -> {
            OrderItem orderItem = OrderItem.builder()
                    .OrderItemId(UUID.randomUUID().toString())
                    .quantity(cartitem.getQuantity())
                    .total_Price(cartitem.getQuantity() * cartitem.getProduct().getDiscountedPrice())
                    .product(cartitem.getProduct())
                    .order(order)
                    .build();
            orderamount.set(orderamount.get() + orderItem.getTotal_Price());
            return orderItem;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);
        order.setOrder_amt(orderamount.get());

        Orders savedOrder = this.orderrepo.save(order);

        // clear cart
        foundcart.getCartItems().clear();
        this.cartrepo.save(foundcart);

        // MANUAL MAPPING to OrderDto
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(savedOrder.getOrderId());
        orderDto.setOrderStatus(savedOrder.getOrderStatus());
        orderDto.setPayment_status(savedOrder.getPayment_status());
        orderDto.setOrder_amt(savedOrder.getOrder_amt());
        orderDto.setBilling_Address(savedOrder.getBilling_Address());
        orderDto.setPhone(savedOrder.getPhone());
        orderDto.setBilling_Name(savedOrder.getBilling_Name());
        orderDto.setOrder_Date(savedOrder.getOrder_Date());
        orderDto.setDelivered_Date(savedOrder.getDelivered_Date());
        orderDto.setUser(savedOrder.getUser());

        List<OrderItemDto> orderItemDtos = savedOrder.getOrderItems().stream().map(item -> {
            OrderItemDto dto = new OrderItemDto();
            dto.setOrderItemId(item.getOrderItemId());
            dto.setQuantity(item.getQuantity());
            dto.setTotal_Price(item.getTotal_Price());

            Product product = item.getProduct();
            ProductDto pdto = new ProductDto();
            pdto.setProductId(product.getProductId());
            pdto.setTitle(product.getTitle());
            pdto.setDescription(product.getDescription());
            pdto.setPrice(product.getPrice());
            pdto.setDiscountedPrice(product.getDiscountedPrice());
            pdto.setQuantity(product.getQuantity());
            pdto.setAddedDate(product.getAddedDate());
            pdto.setLive(product.isLive());
            pdto.setStock(product.isStock());
            pdto.setImageName(product.getImageName());

            dto.setProductdto(pdto);
            return dto;
        }).collect(Collectors.toList());

        orderDto.setOrderItems(orderItemDtos);

        return orderDto;
    }

    @Override
    public String orderremove(String orderid) {
        Orders orderfound = this.orderrepo.findById(orderid)
                .orElseThrow(() -> new ResourceNotFoundException("orderid", "id", orderid));
        this.orderrepo.delete(orderfound);
        return "deleted order";
    }

    @Override
    public List<Orders> allorder(String userid) {
        User user = this.userrepo.findById(userid)
                .orElseThrow(() -> new ResourceNotFoundException("user", "userid", userid));
        return this.orderrepo.findByUser(user);
    }
}
