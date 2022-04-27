package br.revisaoBackEnd_restful.revisaoRestful.service;

import br.revisaoBackEnd_restful.revisaoRestful.model.Student;
import br.revisaoBackEnd_restful.revisaoRestful.model.dto.StudentDto;

import java.util.List;

public interface StudentService {

    List<StudentDto> listAllStudents();
    StudentDto getStudentWithId(int id);
    StudentDto saveNewStudent(Student student);
    void deleteStudent(int id);

}
