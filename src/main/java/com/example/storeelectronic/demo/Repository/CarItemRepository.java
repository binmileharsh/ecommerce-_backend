package com.example.storeelectronic.demo.Repository;

import com.example.storeelectronic.demo.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  CarItemRepository  extends JpaRepository<CartItem,Integer>{
}
