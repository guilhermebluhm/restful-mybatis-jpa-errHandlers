package br.revisaoBackEnd_restful.revisaoRestful.service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StandardError {

    private Long timestamp;
    private Integer error;
    private String message;

}
