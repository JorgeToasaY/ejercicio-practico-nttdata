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
public class AccountRequestDTO implements Serializable {
    @NotBlank(message = "El número de cuenta es obligatorio")
    private String accountNumber;

    @NotBlank(message = "El tipo de cuenta es obligatorio")
    private String accountType;

    @NotNull(message = "El saldo inicial es obligatorio")
    @Digits(integer = 10, fraction = 2, message = "Saldo inválido")
    private BigDecimal initialBalance;

    @NotNull(message = "El estado de la cuenta es obligatorio")
    private Boolean state;

    @NotNull(message = "El ID del cliente es obligatorio")
    private String customerId;
}
