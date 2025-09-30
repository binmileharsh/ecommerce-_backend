package com.example.storeelectronic.demo.entities;

import com.example.storeelectronic.demo.Dto.ProductDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Categories")
public class Category {

    @Id
    private String categoryId;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @Size(max = 1000, message = "Description is too long")
    private String description;


    private String coverImage;

    @OneToMany( mappedBy = "category", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<Product> product=new ArrayList<>();
}

