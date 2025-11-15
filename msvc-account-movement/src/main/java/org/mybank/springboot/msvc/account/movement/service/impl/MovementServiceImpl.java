package org.mybank.springboot.msvc.account.movement.service.impl;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.mybank.springboot.msvc.account.movement.dto.MovementRequestDTO;
import org.mybank.springboot.msvc.account.movement.dto.MovementResponseDTO;
import org.mybank.springboot.msvc.account.movement.dto.MovementUpdateRequestDTO;
import org.mybank.springboot.msvc.account.movement.exception.MovementException;
import org.mybank.springboot.msvc.account.movement.service.MovementService;
import org.mybank.springboot.msvc.account.movement.service.MovementStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovementServiceImpl implements MovementService {

    private static final Logger log = LoggerFactory.getLogger(MovementServiceImpl.class);

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
        log.info("Log del tipo de movimiento {}", type);
        Gson gson = new Gson();
        log.info("Log de las estrategias {}", gson.toJson(strategies.keySet()));
        if (!strategies.containsKey(type)) {
            System.out.println("Ingreso");
            throw new MovementException("Tipo de movimiento inválido: " + type);
        }
        MovementStrategy movementStrategy = strategies.get(type);
        return movementStrategy.createMovement(movement);
    }

    @Override
    public MovementResponseDTO processUpdateMovement(Long id, MovementUpdateRequestDTO movement) {
        String type = movement.getMovementType().toLowerCase(); // "deposit" o "withdraw"
        if (!strategies.containsKey(type)) {
            throw new MovementException("Tipo de movimiento inválido: " + type);
        }
        MovementStrategy movementStrategy = strategies.get(type);
        return movementStrategy.updateMovement(id, movement);
    }


}
