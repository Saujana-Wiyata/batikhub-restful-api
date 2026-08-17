package com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Staff;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class StaffUpdateDTO {

    private String id;

    private String name;

    @Email
    private String email;

    private String password;

    private String role;

}
