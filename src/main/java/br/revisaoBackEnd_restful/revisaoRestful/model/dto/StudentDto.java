package br.revisaoBackEnd_restful.revisaoRestful.model.dto;

import br.revisaoBackEnd_restful.revisaoRestful.model.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class StudentDto {

    private Integer id;
    private String name;
    @JsonIgnore
    private String registry;

    public StudentDto(){

    }

    public StudentDto(Student student){
       this.setId(student.getId());
       this.setName(student.getName());
    }

    public static List<StudentDto> generateListDtoBasedInStudent(Page<Student> student){
        return student.stream().map(StudentDto::new).collect(Collectors.toList());
    }

}
