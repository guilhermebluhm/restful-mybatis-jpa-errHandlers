package br.revisaoBackEnd_restful.revisaoRestful.repository.mapper;

import br.revisaoBackEnd_restful.revisaoRestful.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("SELECT * FROM tb_students")
    List<Student> getAllStudent();


}
