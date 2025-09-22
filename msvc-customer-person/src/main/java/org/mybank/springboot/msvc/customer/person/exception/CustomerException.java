package org.mybank.springboot.msvc.customer.person.exception;

public class CustomerException extends RuntimeException {
    public CustomerException(String message) {
        super(message);
    }
}
