package br.com.springboot.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StandardError {

  private Long timestamp;
  private Integer status;
  private String error;

}
