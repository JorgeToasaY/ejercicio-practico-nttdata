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
public class AccountUpdateRequestDTO implements Serializable {

    @NotNull(message = "El estado de la cuenta es obligatorio")
    private Boolean state;

}
