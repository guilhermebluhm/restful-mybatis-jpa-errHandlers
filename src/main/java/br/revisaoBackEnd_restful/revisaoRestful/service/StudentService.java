package br.revisaoBackEnd_restful.revisaoRestful.service;

import br.revisaoBackEnd_restful.revisaoRestful.model.Student;
import br.revisaoBackEnd_restful.revisaoRestful.model.dto.StudentDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {

    List<StudentDto> listStudents(Pageable page);
    StudentDto getStudentWithId(int id);
    StudentDto saveNewStudent(Student student);
    void deleteStudent(int id);
    StudentDto updateDataStudent(int id, Student student);

}
