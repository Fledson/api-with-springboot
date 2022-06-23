package br.com.springboot.services.exceptions;

public class ObjectNotFoundExeption extends RuntimeException {

  public ObjectNotFoundExeption(String message) {
    super(message);
  }

  public ObjectNotFoundExeption(String message, Throwable cause) {
    super(message, cause);
  }

}
