package org.mybank.springboot.msvc.account.movement.service.impl;

import org.mybank.springboot.msvc.account.movement.dto.AccountRequestDTO;
import org.mybank.springboot.msvc.account.movement.dto.AccountResponseDTO;
import org.mybank.springboot.msvc.account.movement.dto.AccountUpdateRequestDTO;
import org.mybank.springboot.msvc.account.movement.entity.Account;
import org.mybank.springboot.msvc.account.movement.exception.AccountException;
import org.mybank.springboot.msvc.account.movement.mapper.AccountMapper;
import org.mybank.springboot.msvc.account.movement.repository.AccountRepository;
import org.mybank.springboot.msvc.account.movement.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO request) {
        Account account = accountMapper.toEntity(request);
        account.setAvailableBalance(request.getInitialBalance());
        account = accountRepository.save(account);
        return  accountMapper.toDto(account);
    }

    @Override
    public AccountResponseDTO updateAccount(String accountNumber, AccountUpdateRequestDTO request) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountException("Account with accountNumber " + accountNumber + " not found"));
        account.setState(request.getState());
        return accountMapper.toDto(accountRepository.save(account));
    }
    @Override
    public void updateAccountByCustomerId(String customerId, Boolean state) {
        List<Account> listAccount = accountRepository.findByCustomerId(customerId);
        for (Account account : listAccount) {
            account.setState(state);
            accountRepository.save(account);
        }
    }
    @Override
    public List<AccountResponseDTO> getAllAccounts() {
        return accountMapper.toDtoList(accountRepository.findAll());
    }

    @Override
    public AccountResponseDTO getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountException("Account with id " + id + " not found"));
        return accountMapper.toDto(account);

    }

    @Override
    public AccountResponseDTO findByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountException("Account with accountNumber " + accountNumber + " not found"));

        return accountMapper.toDto(account);

    }
    @Override
    public List<Account> findByCustomerId(String customerId){
        List<Account> listAccount = accountRepository.findByCustomerId(customerId);
        if (listAccount.isEmpty()) {
            throw new AccountException("No accounts found for customerId: " + customerId);
        }

        return listAccount;
    }

}
