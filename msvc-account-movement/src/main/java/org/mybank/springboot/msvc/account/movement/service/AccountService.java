package org.mybank.springboot.msvc.account.movement.service;

import org.mybank.springboot.msvc.account.movement.dto.AccountRequestDTO;
import org.mybank.springboot.msvc.account.movement.dto.AccountResponseDTO;
import org.mybank.springboot.msvc.account.movement.dto.AccountUpdateRequestDTO;
import org.mybank.springboot.msvc.account.movement.entity.Account;

import java.util.List;

public interface AccountService {
    AccountResponseDTO createAccount(AccountRequestDTO request);
    AccountResponseDTO updateAccount(String accountNumber, AccountUpdateRequestDTO request);
    List<AccountResponseDTO> getAllAccounts();
    AccountResponseDTO getAccountById(Long id);
    AccountResponseDTO findByAccountNumber(String accountNumber);
    List<Account> findByCustomerId(String customerId);
    void updateAccountByCustomerId(String customerId, Boolean state);
}
