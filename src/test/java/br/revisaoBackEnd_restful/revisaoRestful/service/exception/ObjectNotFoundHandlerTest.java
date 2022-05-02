package br.revisaoBackEnd_restful.revisaoRestful.service.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class ObjectNotFoundHandlerTest {

    @InjectMocks
    ObjectNotFoundHandler hdlr;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void objectNotFoundError() {

        ResponseEntity<StandardError> objectNotFoundError = this.hdlr.objectNotFoundError(
                new ObjectNotFoundError("student not found"));

        Assertions.assertNotNull(objectNotFoundError);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),objectNotFoundError.getStatusCodeValue());
        Assertions.assertEquals(ResponseEntity.class,objectNotFoundError.getClass());
        Assertions.assertEquals(StandardError.class,objectNotFoundError.getBody().getClass());
        Assertions.assertEquals("student not found",objectNotFoundError.getBody().getMessage());
        Assertions.assertEquals(400,objectNotFoundError.getBody().getError());
    }
}