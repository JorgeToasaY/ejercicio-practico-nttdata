package org.mybank.springboot.msvc.account.movement.dto;

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
public class AccountResponseDTO implements Serializable {
    private Long id;
    private String accountNumber;
    private String accountType;
    private BigDecimal initialBalance;
    private Boolean state;
    private String customerId;
    private BigDecimal availableBalance;

}
