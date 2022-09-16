package com.projeto.api.resources.Exceptions;

import com.projeto.api.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler { // manipulador de exceções da camada resource

   @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandartError>objectNotFound(ObjectNotFoundException ex, HttpServletRequest request){
       StandartError error = new StandartError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(),request.getRequestURI());
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
