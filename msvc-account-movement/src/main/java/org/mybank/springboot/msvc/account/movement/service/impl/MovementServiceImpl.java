package org.mybank.springboot.msvc.account.movement.service.impl;

import org.mybank.springboot.msvc.account.movement.dto.MovementRequestDTO;
import org.mybank.springboot.msvc.account.movement.dto.MovementResponseDTO;
import org.mybank.springboot.msvc.account.movement.dto.MovementUpdateRequestDTO;
import org.mybank.springboot.msvc.account.movement.exception.MovementException;
import org.mybank.springboot.msvc.account.movement.service.MovementService;
import org.mybank.springboot.msvc.account.movement.service.MovementStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovementServiceImpl implements MovementService {

    private Map<String, MovementStrategy> strategies;
    @Autowired
    public MovementServiceImpl(List<MovementStrategy> listMovementService) {
        strategies = listMovementService.stream().collect(Collectors.toMap(
                s -> s.getClass().getAnnotation(Service.class).value(),
                s -> s
        ));
    }

    public MovementResponseDTO processCreateMovement(MovementRequestDTO movement) {
        String type = movement.getMovementType().toLowerCase(); // "deposit" o "withdraw"
        MovementStrategy movementStrategy = strategies.get(type);
        if (movementStrategy == null) {
            throw new MovementException("Tipo de movimiento no soportado: " + type);
        }
        return movementStrategy.createMovement(movement);
    }

    @Override
    public MovementResponseDTO processUpdateMovement(Long id, MovementUpdateRequestDTO movement) {
        String type = movement.getMovementType().toLowerCase(); // "deposit" o "withdraw"
        MovementStrategy movementStrategy = strategies.get(type);
        if (movementStrategy == null) {
            throw new MovementException("Tipo de movimiento no soportado: " + type);
        }
        return movementStrategy.updateMovement(id, movement);
    }


}
