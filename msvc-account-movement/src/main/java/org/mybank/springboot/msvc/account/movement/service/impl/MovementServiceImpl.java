package org.mybank.springboot.msvc.account.movement.service.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.mybank.springboot.msvc.account.movement.dto.*;
import org.mybank.springboot.msvc.account.movement.entity.Account;
import org.mybank.springboot.msvc.account.movement.entity.Movement;
import org.mybank.springboot.msvc.account.movement.exception.AccountException;
import org.mybank.springboot.msvc.account.movement.mapper.MovementMapper;
import org.mybank.springboot.msvc.account.movement.repository.AccountRepository;
import org.mybank.springboot.msvc.account.movement.repository.MovementRepository;
import org.mybank.springboot.msvc.account.movement.service.MovementService;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mybank.springboot.msvc.account.movement.Constans.Consants.*;

@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService {

    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;
    private final MovementMapper movementMapper;
    private final RestTemplate restTemplate;
    private final Gson gson = new Gson();

    @Override
    public MovementResponseDTO createMovement(MovementRequestDTO request) {

        Account account = accountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new AccountException("Account with accountNumber " + request.getAccountNumber() + " not found"));
        BigDecimal currentBalance = account.getInitialBalance();
        BigDecimal movementAmount = request.getAmount();
        BigDecimal newBalance;

        if (request.getMovementType().equalsIgnoreCase("Retiro")) {
            if (movementAmount.abs().compareTo(currentBalance)>0) {
                throw new AccountException("Saldo no disponible");
            }
            newBalance = currentBalance.subtract(movementAmount.abs());
        } else {
            newBalance = currentBalance.add(movementAmount.abs());
        }

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

        return movementMapper.toDto(movement);
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

        if (request.getMovementType().equalsIgnoreCase("Retiro")) {
            if (movementAmount.abs().compareTo(currentBalance)>0) {
                throw new AccountException("Saldo no disponible");
            }
            newBalance = currentBalance.subtract(movementAmount.abs());
        } else {
            newBalance = currentBalance.add(movementAmount.abs());
        }

        // Actualiza el saldo en la cuenta
        account.setAvailableBalance(newBalance);
        accountRepository.save(account);

        movement.setDate(LocalDateTime.now());
        movement.setMovementType(request.getMovementType());
        movement.setAmount(movementAmount);
        movement.setBalance(newBalance);

        movementRepository.save(movement);

        return movementMapper.toDto(movement);
    }

    @Override
    public List<MovementResponseDTO> getAllMovements() {
        return movementMapper.toDtoList(movementRepository.findAll());
    }

    @Override
    public List<ReportResponseDTO> getReport(String customerId, LocalDateTime startDate, LocalDateTime endDate) {

        LocalDateTime dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<Account> listAccount = accountRepository.findByCustomerId(customerId);

        List<ReportResponseDTO> report = new ArrayList<>();

        for (Account account : listAccount) {
            List<Movement> movements = movementRepository.findByAccountAndDateBetween(account, startDate, endDate);
            for (Movement m : movements) {
                dateTime = LocalDateTime.parse(m.getDate().toString());
                report.add(new ReportResponseDTO(
                        dateTime.format(formatter),
                        getCustomerName(customerId),
                        account.getAccountNumber(),
                        account.getAccountType(),
                        account.getInitialBalance(),
                        account.getState(),
                        m.getAmount(),
                        m.getBalance()
                ));

            }
        }
        return report;
    }
    private String getCustomerName(String customerId) {
        String url = CUSTOMER_BASE_PATH + SLASH + customerId;
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

            if (response.getBody() != null) {
                CustomerResponseDTO customer = gson.fromJson(response.getBody(), CustomerResponseDTO.class);
                return customer.getName();
            } else {
                throw new AccountException("Customer with customerId " + customerId + " not found");
            }

        } catch (ResourceAccessException e) {
            // Captura excepciones de acceso a red
            throw new AccountException("Error de conexión: " + e.getMessage());
        } catch (HttpClientErrorException e) {
            // Captura errores del cliente, como 4xx
            throw new AccountException("Error HTTP: " + e.getStatusCode());
        } catch (Exception e) {
            throw new AccountException("Exception in getCustomerName " + e);
        }
    }

}
