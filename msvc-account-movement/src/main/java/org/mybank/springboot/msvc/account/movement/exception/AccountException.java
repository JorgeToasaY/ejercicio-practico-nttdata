package org.mybank.springboot.msvc.account.movement.exception;

public class AccountException extends RuntimeException {
    public AccountException(String message) {
        super(message);
    }
}
