package com.ecommerce.web.jpa.e_commerce_web_jpa.model.staff;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffUpdateRequest {

    private String name;

    @Email
    private String email;

    private String password;

    private String role;

}
