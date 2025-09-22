package org.mybank.springboot.msvc.customer.person.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateRequestDTO implements Serializable {

    private String address;

    private String phone;

    private Boolean state;
}
