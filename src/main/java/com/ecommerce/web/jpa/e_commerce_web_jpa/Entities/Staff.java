package com.ecommerce.web.jpa.e_commerce_web_jpa.Entities;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Enum.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// NO RELATION TO ANY ENTITIES

@Entity
@Table(name = "staff")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Staff {

    @Id
    private String id;

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
