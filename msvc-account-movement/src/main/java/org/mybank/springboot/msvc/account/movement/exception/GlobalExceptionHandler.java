package org.mybank.springboot.msvc.account.movement.exception;

import org.mybank.springboot.msvc.account.movement.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ErrorResponseDTO> handleAccountException(AccountException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, request.getRequestURI());
    }
    @ExceptionHandler(MovementException.class)
    public ResponseEntity<ErrorResponseDTO> handleMovementException(MovementException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.UNPROCESSABLE_ENTITY, request.getRequestURI());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneric(Exception ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, request.getRequestURI());
    }
    private ResponseEntity<ErrorResponseDTO> buildErrorResponse(Exception ex, HttpStatus status, String path) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                path
        );
        return new ResponseEntity<>(errorResponse, status);
    }
}
