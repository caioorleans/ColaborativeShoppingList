package com.github.caioorleans.familytodo.exception.exceptionHandler;

import com.github.caioorleans.familytodo.exception.EmailAlreadyInUseException;
import com.github.caioorleans.familytodo.exception.ForbiddenException;
import com.github.caioorleans.familytodo.exception.NotFoundException;
import com.github.caioorleans.familytodo.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = error instanceof FieldError
                    ? ((FieldError) error).getField()
                    : error.getObjectName();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("errors", errors);
        body.put("message", "Erro de validação nos dados enviados");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            NotFoundException.class,
            UnauthorizedException.class,
            EmailAlreadyInUseException.class,
            ForbiddenException.class,
    })
    public ResponseEntity<Map<String, Object>> handleCustomExceptions(RuntimeException ex) {
        HttpStatus status = switch (ex) {
            case NotFoundException ignored -> HttpStatus.NOT_FOUND;
            case UnauthorizedException ignored -> HttpStatus.UNAUTHORIZED;
            case EmailAlreadyInUseException ignored -> HttpStatus.CONFLICT;
            case ForbiddenException ignored -> HttpStatus.FORBIDDEN;
            default -> throw new IllegalStateException("Unexpected value: " + ex);
        };

        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, status);
    }

}

