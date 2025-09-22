package org.mybank.springboot.msvc.account.movement.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovementRequestDTO implements Serializable {
    @NotBlank(message = "El número de cuenta es obligatorio")
    private String accountNumber;
    @NotBlank(message = "El tipo de cuenta es obligatorio")
    private String movementType;
    @NotNull(message = "El monto del movimiento es obligatorio")
    @Digits(integer = 10, fraction = 2, message = "Monto inválido")
    private BigDecimal amount;
}
