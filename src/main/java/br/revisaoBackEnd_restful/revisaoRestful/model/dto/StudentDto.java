package br.revisaoBackEnd_restful.revisaoRestful.model.dto;

import br.revisaoBackEnd_restful.revisaoRestful.model.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {

    private Integer id;
    private String name;
    @JsonIgnore
    private String registry;

    public StudentDto(Student student){
       this.setId(student.getId());
       this.setName(student.getName());
    }


}
