package br.revisaoBackEnd_restful.revisaoRestful.service.exception;

public class ObjectNotFoundError extends RuntimeException{

    public ObjectNotFoundError(String message) {
        super(message);
    }

    public ObjectNotFoundError(String message, Throwable cause) {
        super(message, cause);
    }
}
