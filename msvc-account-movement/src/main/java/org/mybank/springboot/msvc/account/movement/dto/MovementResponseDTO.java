package org.mybank.springboot.msvc.account.movement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovementResponseDTO implements Serializable {
    private Long id;
    private LocalDateTime date;
    private String movementType;
    private BigDecimal amount;
    private BigDecimal balance;
    private String accountNumber;
}
