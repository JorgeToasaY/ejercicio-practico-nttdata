package org.mybank.springboot.msvc.account.movement.service;

import org.mybank.springboot.msvc.account.movement.dto.MovementRequestDTO;
import org.mybank.springboot.msvc.account.movement.dto.MovementResponseDTO;
import org.mybank.springboot.msvc.account.movement.dto.MovementUpdateRequestDTO;
import org.mybank.springboot.msvc.account.movement.dto.ReportResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface MovementService {
    MovementResponseDTO createMovement(MovementRequestDTO request);
    MovementResponseDTO updateMovement(Long id, MovementUpdateRequestDTO request);
    List<MovementResponseDTO> getAllMovements();
    List<ReportResponseDTO> getReport(String customerIdentification, LocalDateTime startDate, LocalDateTime endDate);
}
