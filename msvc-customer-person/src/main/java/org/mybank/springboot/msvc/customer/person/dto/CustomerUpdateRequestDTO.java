package org.mybank.springboot.msvc.customer.person.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateRequestDTO implements Serializable {

    @NotBlank(message = "La direccion es obligatorio")
    @Size(max = 50, message = "La dirección no puede tener más de 50 caracteres")
    private String address;

    @NotBlank(message = "El telefono es obligatorio")
    @Size(max = 10, message = "El teléfono no puede tener más de 10 caracteres")
    private String phone;

    @NotNull(message = "El estado es obligatorio")
    private Boolean state;
}
