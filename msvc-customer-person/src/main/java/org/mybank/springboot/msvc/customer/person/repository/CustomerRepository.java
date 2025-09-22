package org.mybank.springboot.msvc.customer.person.repository;

import org.mybank.springboot.msvc.customer.person.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCustomerId(String customerId);
    void deleteByCustomerId(String customerId);
    boolean existsByIdentification(String identification);
}
