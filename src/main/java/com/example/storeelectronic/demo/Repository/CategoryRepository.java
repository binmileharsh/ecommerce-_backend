package com.example.storeelectronic.demo.Repository;

import com.example.storeelectronic.demo.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String> {
}
