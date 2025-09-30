package com.example.storeelectronic.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {

    @Id
    @NotBlank(message = "User ID is required")
    private String userId;

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[A-Za-z ]{2,50}$", message = "Name must contain only alphabets and spaces (2-50 chars)")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
            message = "Password must contain uppercase, lowercase, digit and special char")
    private String password;

    @NotBlank(message = "Gender is required")

    @Pattern(regexp = "^(?i)(male|female|other)$", message = "Gender must be Male, Female or Other")
    private String gender;

    @Size(max = 1000, message = "About section cannot exceed 1000 characters")
    private String about;

    private String imageName;


    @OneToMany(fetch = FetchType.LAZY)
    private List<Orders> orders =new ArrayList<>();
}
