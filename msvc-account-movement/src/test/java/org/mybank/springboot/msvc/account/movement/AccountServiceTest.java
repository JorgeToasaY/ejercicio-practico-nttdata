package org.mybank.springboot.msvc.account.movement;

import lombok.extern.slf4j.Slf4j;
import org.mybank.springboot.msvc.account.movement.dto.AccountRequestDTO;
import org.mybank.springboot.msvc.account.movement.dto.AccountResponseDTO;
import org.mybank.springboot.msvc.account.movement.entity.Account;
import org.mybank.springboot.msvc.account.movement.mapper.AccountMapper;
import org.mybank.springboot.msvc.account.movement.repository.AccountRepository;
import org.mybank.springboot.msvc.account.movement.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
@Slf4j
class AccountServiceTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @Test
    void testCreateAccount() {
        // Arrange
        AccountRequestDTO request = AccountRequestDTO.builder()
                .accountNumber("123456002")
                .accountType("Ahorros")
                .initialBalance(BigDecimal.valueOf(1000))
                .state(true)
                .customerId("CUST000013")
                .build();

        Account entity = new Account();
        entity.setAccountNumber(request.getAccountNumber());
        entity.setAccountType(request.getAccountType());
        entity.setInitialBalance(request.getInitialBalance());
        entity.setState(request.getState());
        entity.setCustomerId(request.getCustomerId());

        AccountResponseDTO response = AccountResponseDTO.builder()
                .accountNumber("123456002")
                .accountType("Ahorros")
                .initialBalance(BigDecimal.valueOf(1000))
                .state(true)
                .initialBalance(BigDecimal.valueOf(1000))
                .customerId("CUST000013")
                .build();

        Mockito.when(accountMapper.toAccount(request)).thenReturn(entity);
        Mockito.when(accountRepository.save(entity)).thenReturn(entity);
        Mockito.when(accountMapper.toAccountResponseDTO(entity)).thenReturn(response);

        // Act
        AccountResponseDTO result = accountService.createAccount(request);
        log.info("Resultado del test -> accountNumber: {}, customerId: {}", result.getAccountNumber(), result.getCustomerId());
        // Assert
        Assertions.assertEquals("123456002", result.getAccountNumber());

    }
}