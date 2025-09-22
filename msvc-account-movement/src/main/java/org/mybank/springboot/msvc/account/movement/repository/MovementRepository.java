package org.mybank.springboot.msvc.account.movement.repository;

import org.mybank.springboot.msvc.account.movement.entity.Account;
import org.mybank.springboot.msvc.account.movement.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long> {
    List<Movement> findByAccountAndDateBetween(Account account, LocalDateTime startDate, LocalDateTime endDate);
}
