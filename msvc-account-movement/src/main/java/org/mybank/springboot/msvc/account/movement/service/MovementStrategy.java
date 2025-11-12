package org.mybank.springboot.msvc.account.movement.service;

import org.mybank.springboot.msvc.account.movement.dto.MovementRequestDTO;
import org.mybank.springboot.msvc.account.movement.dto.MovementResponseDTO;
import org.mybank.springboot.msvc.account.movement.dto.MovementUpdateRequestDTO;

public interface MovementStrategy {
    MovementResponseDTO createMovement(MovementRequestDTO request);

    MovementResponseDTO updateMovement(Long id, MovementUpdateRequestDTO request);
}
