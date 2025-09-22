package org.mybank.springboot.msvc.account.movement.controller;

import org.mybank.springboot.msvc.account.movement.dto.AccountRequestDTO;
import org.mybank.springboot.msvc.account.movement.dto.AccountResponseDTO;
import org.mybank.springboot.msvc.account.movement.dto.AccountUpdateRequestDTO;
import org.mybank.springboot.msvc.account.movement.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponseDTO> create(@RequestBody @Validated AccountRequestDTO request) {
        return new ResponseEntity<>(accountService.createAccount(request), HttpStatus.CREATED);
    }

    @PatchMapping("/{accountNumber}")
    public ResponseEntity<AccountResponseDTO> update(@PathVariable String accountNumber, @RequestBody @Validated AccountUpdateRequestDTO request) {
        return ResponseEntity.ok(accountService.updateAccount(accountNumber, request));
    }
    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getAll() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountResponseDTO> getByAccountNumber(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.findByAccountNumber(accountNumber));
    }
}
