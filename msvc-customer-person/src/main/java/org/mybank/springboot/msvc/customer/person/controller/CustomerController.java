package org.mybank.springboot.msvc.customer.person.controller;

import jakarta.validation.Valid;
import org.mybank.springboot.msvc.customer.person.dto.CustomerRequestDTO;
import org.mybank.springboot.msvc.customer.person.dto.CustomerResponseDTO;
import org.mybank.springboot.msvc.customer.person.dto.CustomerUpdateRequestDTO;
import org.mybank.springboot.msvc.customer.person.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody @Valid CustomerRequestDTO customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customer));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDTO> getCustomer(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.getCustomerByCustomerId(customerId));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> listCustomers() {

        return ResponseEntity.ok(customerService.listCustomers());
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable String customerId, @RequestBody  @Valid CustomerUpdateRequestDTO customer) {
        return ResponseEntity.ok(customerService.updateCustomer(customerId, customer));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String customerId) throws Exception {
        customerService.deleteCustomerByCustomerId(customerId);
        return ResponseEntity.ok("Customer with customerId " + customerId + " eliminated");
    }

}
