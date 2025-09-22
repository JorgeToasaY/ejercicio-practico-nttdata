package org.mybank.springboot.msvc.account.movement.repository;

import org.mybank.springboot.msvc.account.movement.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findById(Long id);
    Account save(Account account);
    void deleteById(Long id);
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findByCustomerId(String customerId);
}
