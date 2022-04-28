package br.revisaoBackEnd_restful.revisaoRestful;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RevisaoRestfulApplicationTest {


    @Test
    @DisplayName("testando o entry point")
    void whenMainMethodIsCalling() {
        RevisaoRestfulApplication.main(new String[]{});
    }
}