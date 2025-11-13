package org.mybank.springboot.msvc.account.movement.controller;

import lombok.RequiredArgsConstructor;
import org.mybank.springboot.msvc.account.movement.dto.MovementRequestDTO;
import org.mybank.springboot.msvc.account.movement.dto.MovementResponseDTO;
import org.mybank.springboot.msvc.account.movement.dto.MovementUpdateRequestDTO;
import org.mybank.springboot.msvc.account.movement.dto.ReportResponseDTO;
import org.mybank.springboot.msvc.account.movement.exception.MovementException;
import org.mybank.springboot.msvc.account.movement.service.MovementService;
import org.mybank.springboot.msvc.account.movement.service.ReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
public class MovementController {

    private final MovementService movementService;
    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<MovementResponseDTO> create(@RequestBody @Validated MovementRequestDTO request) {
        System.out.println("➡️ Entrando al método processCreateMovement con tipo: ");
        return new ResponseEntity<>(movementService.processCreateMovement(request), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MovementResponseDTO> update(@PathVariable Long id, @RequestBody @Validated MovementUpdateRequestDTO request) {
        return new ResponseEntity<>(movementService.processUpdateMovement(id, request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MovementResponseDTO>> getAll() {
        return ResponseEntity.ok(reportService.getAllMovements());
    }

    @GetMapping("/reportes")
    public ResponseEntity<List<ReportResponseDTO>> getReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta,
            @RequestParam String customer) {
        return ResponseEntity.ok(reportService.getReport(customer, desde, hasta));
    }
}