package org.mybank.springboot.msvc.account.movement.service.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.mybank.springboot.msvc.account.movement.dto.CustomerResponseDTO;
import org.mybank.springboot.msvc.account.movement.dto.MovementResponseDTO;
import org.mybank.springboot.msvc.account.movement.dto.ReportResponseDTO;
import org.mybank.springboot.msvc.account.movement.entity.Account;
import org.mybank.springboot.msvc.account.movement.entity.Movement;
import org.mybank.springboot.msvc.account.movement.exception.AccountException;
import org.mybank.springboot.msvc.account.movement.mapper.MovementMapper;
import org.mybank.springboot.msvc.account.movement.repository.AccountRepository;
import org.mybank.springboot.msvc.account.movement.repository.MovementRepository;
import org.mybank.springboot.msvc.account.movement.service.ReportService;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mybank.springboot.msvc.account.movement.Constans.Consants.CUSTOMER_BASE_PATH;
import static org.mybank.springboot.msvc.account.movement.Constans.Consants.SLASH;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;
    private final MovementMapper movementMapper;
    private final RestTemplate restTemplate;
    private final Gson gson = new Gson();
    @Override
    public List<MovementResponseDTO> getAllMovements() {
        return movementMapper.toMovementResponseDtoList(movementRepository.findAll());
    }

    @Override
    public List<ReportResponseDTO> getReport(String customerId, LocalDateTime startDate, LocalDateTime endDate) {
        LocalDateTime dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        List<Account> listAccount = accountRepository.findByCustomerId(customerId);
        String customerName =    getCustomerName(customerId);

        List<ReportResponseDTO> report = new ArrayList<>();

        for (Account account : listAccount) {
            List<Movement> movements = movementRepository.findByAccountAndDateBetween(account, startDate, endDate);
            for (Movement movement : movements) {
                report.add(movementMapper.toReportMovementDto(movement,customerName));
                //dateTime = LocalDateTime.parse(movement.getDate().toString());

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
