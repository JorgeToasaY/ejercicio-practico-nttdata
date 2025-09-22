package org.mybank.springboot.msvc.account.movement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String accountNumber;
    @Column(nullable = false)
    private String accountType;
    @Column(nullable = false)
    private BigDecimal initialBalance;
    @Column(nullable = false)
    private Boolean state;
    @Column(nullable = false)
    private String customerId;
    @Column(nullable=false)
    private BigDecimal availableBalance;
}
