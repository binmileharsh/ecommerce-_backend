package com.example.storeelectronic.demo.Repository;

import com.example.storeelectronic.demo.entities.Cart;
import com.example.storeelectronic.demo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Rolerepo extends JpaRepository<Role,String> {
    Optional<Role>  findByName(String name);
}
