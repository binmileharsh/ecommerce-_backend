package com.example.storeelectronic.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {
    @Id
    private String roleId;
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private List<User> users=new ArrayList<>();
}
