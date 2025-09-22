package org.mybank.springboot.msvc.account.movement.mapper;

import org.mybank.springboot.msvc.account.movement.dto.AccountRequestDTO;
import org.mybank.springboot.msvc.account.movement.dto.AccountResponseDTO;
import org.mybank.springboot.msvc.account.movement.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toEntity(AccountRequestDTO dto);

    AccountResponseDTO toDto(Account entity);

    List<AccountResponseDTO> toDtoList(List<Account> entities);
}
