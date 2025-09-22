package org.mybank.springboot.msvc.customer.person;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import lombok.extern.slf4j.Slf4j;
import org.mybank.springboot.msvc.customer.person.dto.CustomerResponseDTO;
import org.mybank.springboot.msvc.customer.person.entity.Customer;
import org.mybank.springboot.msvc.customer.person.exception.CustomerException;
import org.mybank.springboot.msvc.customer.person.mapper.CustomerMapper;
import org.mybank.springboot.msvc.customer.person.repository.CustomerRepository;
import org.mybank.springboot.msvc.customer.person.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
@ExtendWith(MockitoExtension.class)
@Slf4j
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void testGetCustomerByCustomerId() {
        //Arrange
        String customerId = "C-002";
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setName("Ana");

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setCustomerId(customerId);
        customerResponseDTO.setName("Ana");

        when(customerRepository.findByCustomerId(customerId)).thenReturn(Optional.of(customer));
        when(customerMapper.toDto(customer)).thenReturn(customerResponseDTO);

        //Act
        CustomerResponseDTO result = customerService.getCustomerByCustomerId(customerId);

        //Assert
        assertNotNull(result);
        assertEquals(customerId, result.getCustomerId());
        assertEquals("Ana", result.getName());
        log.info("Resultado del test -> customerId: {}, name: {}", result.getCustomerId(), result.getName());
        verify(customerRepository, times(1)).findByCustomerId(customerId);
        verify(customerMapper, times(1)).toDto(customer);
    }

    @Test
    void testGetCustomerByCustomerIdNotFound() {
        String customerId = "CUST0051";

        when(customerRepository.findByCustomerId(customerId)).thenReturn(Optional.empty());
        CustomerResponseDTO result = new CustomerResponseDTO();
        CustomerException exception = assertThrows(CustomerException.class, () -> {
            result: customerService.getCustomerByCustomerId(customerId);
        });

        assertEquals("Customer with id CUST0051 not found", exception.getMessage());
        log.info("Resultado del test -> customerId: {}, name: {}", result.getCustomerId(), result.getName());
        verify(customerRepository, times(1)).findByCustomerId(customerId);
        verify(customerMapper, never()).toDto(any());
    }
}