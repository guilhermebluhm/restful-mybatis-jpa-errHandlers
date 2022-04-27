package br.revisaoBackEnd_restful.revisaoRestful;

import br.revisaoBackEnd_restful.revisaoRestful.model.Student;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MappedTypes(Student.class)
@MapperScan("br.revisaoBackEnd_restful.revisaoRestful.repository.mapper")
@SpringBootApplication
public class RevisaoRestfulApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevisaoRestfulApplication.class, args);
	}

}
