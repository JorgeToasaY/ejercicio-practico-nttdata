package org.mybank.springboot.msvc.account.movement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mybank.springboot.msvc.account.movement.dto.MovementRequestDTO;
import org.mybank.springboot.msvc.account.movement.dto.MovementResponseDTO;
import org.mybank.springboot.msvc.account.movement.dto.ReportResponseDTO;
import org.mybank.springboot.msvc.account.movement.entity.Movement;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovementMapper {

    Movement toEntity(MovementRequestDTO dto);
    @Mapping(source = "account.accountNumber", target = "accountNumber")
    MovementResponseDTO toMovementResponseDto(Movement entity);

    @Mapping(source = "entity.date", target = "date")
    @Mapping(source = "customerName", target = "customerName")
    @Mapping(source = "entity.account.accountNumber", target = "accountNumber")
    @Mapping(source = "entity.account.accountType", target = "accountType")
    @Mapping(source = "entity.account.initialBalance", target = "initialBalance")
    @Mapping(source = "entity.account.state", target = "state")
    @Mapping(source = "entity.amount", target = "movement")
    @Mapping(source = "entity.balance", target = "availableBalance")
    ReportResponseDTO toReportMovementDto(Movement entity, String customerName);

    List<MovementResponseDTO> toMovementResponseDtoList(List<Movement> entities);
}
