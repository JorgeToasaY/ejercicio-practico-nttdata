package org.mybank.springboot.msvc.customer.person.service;

import org.mybank.springboot.msvc.customer.person.dto.CustomerRequestDTO;
import org.mybank.springboot.msvc.customer.person.dto.CustomerResponseDTO;
import org.mybank.springboot.msvc.customer.person.dto.CustomerUpdateRequestDTO;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO getCustomerByCustomerId(String customerId);
    List<CustomerResponseDTO> listCustomers();
    CustomerResponseDTO updateCustomer(String customerId, CustomerUpdateRequestDTO customerRequestDTO);
    void deleteCustomerByCustomerId(String id);
}
