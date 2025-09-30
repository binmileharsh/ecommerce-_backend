package com.example.storeelectronic.demo.Repository;

import com.example.storeelectronic.demo.Dto.PageableResponse;
import com.example.storeelectronic.demo.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {

    Page<Product> findByLiveTrue(Pageable pageable);
    Page<Product> findByTitleContaining( Pageable pageable,String subtitle);
    Page<Product> findByCategory_CategoryId(String categoryId, Pageable pageable);

}
