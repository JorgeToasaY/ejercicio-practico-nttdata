package org.mybank.springboot.msvc.account.movement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponseDTO implements Serializable {
    private String date;
    private String customerName;
    private String accountNumber;
    private String accountType;
    private BigDecimal initialBalance;
    private Boolean state;
    private BigDecimal movement;
    private BigDecimal availableBalance;
}
