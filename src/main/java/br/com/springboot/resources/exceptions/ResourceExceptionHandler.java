package br.com.springboot.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.springboot.services.exceptions.ObjectNotFoundExeption;

/**
 * Classe manupuladora de erros
 */
@ControllerAdvice
public class ResourceExceptionHandler {
  /**
   * Classe que formata meu erro de acordo com o que ocorreu
   * 
   * @param error recebe um objeto error ObjectNotFoundExeption
   * @return Retorna um StandardError
   */
  @ExceptionHandler(ObjectNotFoundExeption.class)
  public ResponseEntity<StandardError> objectNotFoundExeption(ObjectNotFoundExeption error) {

    StandardError errorObject = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
        error.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorObject);
  }
}
