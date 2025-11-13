package org.mybank.springboot.msvc.customer.person.mapper;

import org.mapstruct.Mapper;
import org.mybank.springboot.msvc.customer.person.dto.CustomerRequestDTO;
import org.mybank.springboot.msvc.customer.person.dto.CustomerResponseDTO;
import org.mybank.springboot.msvc.customer.person.entity.Customer;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toCustomer(CustomerRequestDTO dto);
    CustomerResponseDTO toCustomerResponseDTO(Customer entity);

    List<CustomerResponseDTO> toCustomerResponseDTOList(List<Customer> entities);
}
