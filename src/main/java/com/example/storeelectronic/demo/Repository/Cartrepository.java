package com.example.storeelectronic.demo.Repository;

import com.example.storeelectronic.demo.entities.Cart;
import com.example.storeelectronic.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Cartrepository extends JpaRepository<Cart,String> {
   Optional<Cart> findByUser(User user);

}
