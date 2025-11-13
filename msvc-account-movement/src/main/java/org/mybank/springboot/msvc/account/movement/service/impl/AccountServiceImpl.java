package org.mybank.springboot.msvc.account.movement.service.impl;

import lombok.RequiredArgsConstructor;
import org.mybank.springboot.msvc.account.movement.dto.AccountRequestDTO;
import org.mybank.springboot.msvc.account.movement.dto.AccountResponseDTO;
import org.mybank.springboot.msvc.account.movement.dto.AccountUpdateRequestDTO;
import org.mybank.springboot.msvc.account.movement.entity.Account;
import org.mybank.springboot.msvc.account.movement.exception.AccountException;
import org.mybank.springboot.msvc.account.movement.mapper.AccountMapper;
import org.mybank.springboot.msvc.account.movement.repository.AccountRepository;
import org.mybank.springboot.msvc.account.movement.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO request) {
        Account account = accountMapper.toAccount(request);
        account.setAvailableBalance(request.getInitialBalance());
        account = accountRepository.save(account);
        return  accountMapper.toAccountResponseDTO(account);
    }

    @Override
    public AccountResponseDTO updateAccount(String accountNumber, AccountUpdateRequestDTO request) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountException("Account with accountNumber " + accountNumber + " not found"));
        account.setState(request.getState());
        return accountMapper.toAccountResponseDTO(accountRepository.save(account));
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
        return accountMapper.toAccountResponseDTOList(accountRepository.findAll());
    }


    @Override
    public AccountResponseDTO findByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountException("Account with accountNumber " + accountNumber + " not found"));

        return accountMapper.toAccountResponseDTO(account);

    }

}
