package com.example.storeelectronic.demo.Repository;

import com.example.storeelectronic.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.List;


public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByEmail(String email);
    List<User> findByNameContainingIgnoreCase(String nameKeyword);

}
