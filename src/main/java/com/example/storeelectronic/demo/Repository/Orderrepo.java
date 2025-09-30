package com.example.storeelectronic.demo.Repository;

import com.example.storeelectronic.demo.entities.Orders;
import com.example.storeelectronic.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Orderrepo extends JpaRepository<Orders ,String> {
    List<Orders> findByUser(User user);
}
