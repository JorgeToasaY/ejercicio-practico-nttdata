package org.mybank.springboot.msvc.account.movement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO implements Serializable {
    private String customerId;

    private String name;

    private String gender;

    private Integer age;

    private String identification;

    private String address;

    private String phone;

    private String password;

    private Boolean state;
}
