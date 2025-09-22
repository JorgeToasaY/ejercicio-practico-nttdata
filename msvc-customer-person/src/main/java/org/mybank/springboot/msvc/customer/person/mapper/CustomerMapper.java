package org.mybank.springboot.msvc.customer.person.mapper;

import org.mybank.springboot.msvc.customer.person.dto.CustomerRequestDTO;
import org.mybank.springboot.msvc.customer.person.dto.CustomerResponseDTO;
import org.mybank.springboot.msvc.customer.person.dto.CustomerUpdateRequestDTO;
import org.mybank.springboot.msvc.customer.person.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerRequestDTO dto);
    Customer toEntity(CustomerUpdateRequestDTO dto);
    CustomerResponseDTO toDto(Customer entity);

    List<CustomerResponseDTO> toDtoList(List<Customer> entities);
}
