package org.mybank.springboot.msvc.customer.person.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Customer extends Person {
    @Column(unique = true, nullable = false)
    private String customerId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean state;
}
