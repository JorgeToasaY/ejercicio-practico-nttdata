package org.mybank.springboot.msvc.account.movement.service.impl;

import lombok.RequiredArgsConstructor;
import org.mybank.springboot.msvc.account.movement.dto.MovementRequestDTO;
import org.mybank.springboot.msvc.account.movement.dto.MovementResponseDTO;
import org.mybank.springboot.msvc.account.movement.dto.MovementUpdateRequestDTO;
import org.mybank.springboot.msvc.account.movement.entity.Account;
import org.mybank.springboot.msvc.account.movement.entity.Movement;
import org.mybank.springboot.msvc.account.movement.exception.AccountException;
import org.mybank.springboot.msvc.account.movement.exception.MovementException;
import org.mybank.springboot.msvc.account.movement.mapper.MovementMapper;
import org.mybank.springboot.msvc.account.movement.repository.AccountRepository;
import org.mybank.springboot.msvc.account.movement.repository.MovementRepository;
import org.mybank.springboot.msvc.account.movement.service.MovementStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service("retiro")
@RequiredArgsConstructor
public class WithdrawalMovement implements MovementStrategy {

    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;
    private final MovementMapper movementMapper;


    @Override
    public MovementResponseDTO createMovement(MovementRequestDTO request) {

        Account account = accountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new AccountException("Account with accountNumber " + request.getAccountNumber() + " not found"));
        BigDecimal currentBalance = account.getAvailableBalance();
        BigDecimal movementAmount = request.getAmount();
        BigDecimal newBalance;

        if (movementAmount.abs().compareTo(currentBalance)>0) {
            throw new MovementException("Saldo no disponible");
        }
        newBalance = currentBalance.subtract(movementAmount.abs());

        // Actualiza el saldo en la cuenta
        account.setAvailableBalance(newBalance);
        accountRepository.save(account);

        Movement movement = new Movement();
        movement.setAccount(account);
        movement.setDate(LocalDateTime.now());
        movement.setMovementType(request.getMovementType());
        movement.setAmount(movementAmount);
        movement.setBalance(newBalance);

        movementRepository.save(movement);

        return movementMapper.toMovementResponseDto(movement);
    }
    @Override
    public MovementResponseDTO updateMovement(Long id, MovementUpdateRequestDTO request) {

        Movement movement = movementRepository.findById(id)
                .orElseThrow(() -> new AccountException("Movement with id " + id + " not found"));

        Account account = accountRepository.findById(movement.getAccount().getId())
                .orElseThrow(() -> new AccountException("Account with accountNumber " + movement.getAccount().getAccountNumber() + " not found"));

        BigDecimal currentBalance = account.getInitialBalance();
        BigDecimal movementAmount = request.getAmount();
        BigDecimal newBalance;

        if (movementAmount.abs().compareTo(currentBalance)>0) {
            throw new AccountException("Saldo no disponible");
        }
        newBalance = currentBalance.subtract(movementAmount.abs());

        // Actualiza el saldo en la cuenta
        account.setAvailableBalance(newBalance);
        accountRepository.save(account);

        movement.setDate(LocalDateTime.now());
        movement.setMovementType(request.getMovementType());
        movement.setAmount(movementAmount);
        movement.setBalance(newBalance);

        movementRepository.save(movement);

        return movementMapper.toMovementResponseDto(movement);
    }
}
