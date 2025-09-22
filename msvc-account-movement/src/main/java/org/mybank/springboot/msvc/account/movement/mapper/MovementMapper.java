package org.mybank.springboot.msvc.account.movement.mapper;

import org.mapstruct.Mapping;
import org.mybank.springboot.msvc.account.movement.dto.MovementRequestDTO;
import org.mybank.springboot.msvc.account.movement.dto.MovementResponseDTO;
import org.mybank.springboot.msvc.account.movement.entity.Movement;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovementMapper {

    Movement toEntity(MovementRequestDTO dto);
    @Mapping(source = "account.accountNumber", target = "accountNumber")
    MovementResponseDTO toDto(Movement entity);

    List<MovementResponseDTO> toDtoList(List<Movement> entities);
}
