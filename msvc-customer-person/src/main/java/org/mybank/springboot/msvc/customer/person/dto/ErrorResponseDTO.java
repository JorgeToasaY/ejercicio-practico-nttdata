package org.mybank.springboot.msvc.customer.person.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO implements Serializable {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}