package org.mybank.springboot.msvc.account.movement.exception;

public class MovementException extends RuntimeException {
    public MovementException(String message) {
        super(message);
    }
}
