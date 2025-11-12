package org.mybank.springboot.msvc.customer.person.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDTO implements Serializable {
    @NotBlank(message = "El Id del cliente es obligatorio")
    @Size(max = 50, message = "El Id del cliente no puede tener más de 50 caracteres")
    private String customerId;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    private String name;

    @NotBlank(message = "El genero es obligatorio")
    @Size(max = 15, message = "El género no puede tener más de 15 caracteres")
    private String gender;

    @NotNull(message = "La edad es obligatorio")
    @Min(value = 1, message = "La edad mínima permitida es 1")
    @Max(value = 100, message = "La edad máxima permitida es 100")
    private Integer age;

    @NotBlank(message = "La identificacion es obligatorio")
    @Size(max = 13, message ="La identificación no puede tener más de 13 caracteres")
    private String identification;

    @NotBlank(message = "La direccion es obligatorio")
    @Size(max = 50, message = "La dirección no puede tener más de 50 caracteres")
    private String address;

    @NotBlank(message = "El telefono es obligatorio")
    @Size(max = 10, message = "El teléfono no puede tener más de 10 caracteres")
    private String phone;

    @NotBlank(message = "La contraseña es obligatorio")
    @Size(max = 15, message = "La contraseña no puede tener más de 15 caracteres")
    private String password;

    @NotNull(message = "El estado es obligatorio")
    private Boolean state;
}
