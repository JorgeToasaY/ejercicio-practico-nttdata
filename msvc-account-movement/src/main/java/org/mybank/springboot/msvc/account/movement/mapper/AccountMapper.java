package org.mybank.springboot.msvc.account.movement.mapper;

import org.mybank.springboot.msvc.account.movement.dto.AccountRequestDTO;
import org.mybank.springboot.msvc.account.movement.dto.AccountResponseDTO;
import org.mybank.springboot.msvc.account.movement.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toAccount(AccountRequestDTO dto);

    AccountResponseDTO toAccountResponseDTO(Account entity);

    List<AccountResponseDTO> toAccountResponseDTOList(List<Account> entities);
}
