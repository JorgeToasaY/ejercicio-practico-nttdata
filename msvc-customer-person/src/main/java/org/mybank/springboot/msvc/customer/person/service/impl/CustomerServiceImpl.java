package org.mybank.springboot.msvc.customer.person.service.impl;

import org.mybank.springboot.msvc.customer.person.dto.CustomerRequestDTO;
import org.mybank.springboot.msvc.customer.person.dto.CustomerResponseDTO;
import org.mybank.springboot.msvc.customer.person.dto.CustomerUpdateRequestDTO;
import org.mybank.springboot.msvc.customer.person.entity.Customer;
import org.mybank.springboot.msvc.customer.person.event.CustomerEventPublisher;
import org.mybank.springboot.msvc.customer.person.exception.CustomerException;
import org.mybank.springboot.msvc.customer.person.mapper.CustomerMapper;
import org.mybank.springboot.msvc.customer.person.repository.CustomerRepository;
import org.mybank.springboot.msvc.customer.person.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerEventPublisher customerEventPublisher;


    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {
        if (customerRepository.existsByIdentification(customerRequestDTO.getIdentification()))
            throw  new CustomerException("Customer with identification " + customerRequestDTO.getIdentification() + " ya existe");

        Customer customer = customerMapper.toCustomer(customerRequestDTO);
        /*rabbit.convertAndSend(EXCHANGE, "customer.created",
                new CustomerEvent(saved.getCustomerId(), saved.getNombre(), "CREATED"));*/
        return customerMapper.toCustomerResponseDTO(customerRepository.save(customer));

    }

    @Override
    public CustomerResponseDTO getCustomerByCustomerId(String customerId) {
        Customer customer = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CustomerException("Customer with id " + customerId + " not found"));
        return customerMapper.toCustomerResponseDTO(customer);
    }

    @Override
    public List<CustomerResponseDTO> listCustomers() {

        return customerMapper.toCustomerResponseDTOList(customerRepository.findAll());
    }

    @Override
    public CustomerResponseDTO updateCustomer(String customerId, CustomerUpdateRequestDTO customerUpdate) {
        Customer customer = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CustomerException("Customer with id " + customerId + " not found"));
        Boolean customerStateBD = customer.getState();
        customer.setState(customerUpdate.getState());
        customer.setAddress(customerUpdate.getAddress());
        customer.setPhone(customerUpdate.getPhone());
        CustomerResponseDTO customerResponseDTO = customerMapper.toCustomerResponseDTO(customerRepository.save(customer));
        // Enviar evento al otro microservicio
        if(customerStateBD.compareTo(customerUpdate.getState()) !=0) {
            //System.out.println("Enviado: " + customer);
            customerEventPublisher.sendCustomerUpdateEvent(customerResponseDTO);
        }
        return customerResponseDTO;


    }

    @Override
    @Transactional
    public void deleteCustomerByCustomerId(String customerId) {
        Customer customer = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CustomerException("Customer with customerId " + customerId + " not found"));
        customerRepository.deleteByCustomerId(customer.getCustomerId());


    }
}
