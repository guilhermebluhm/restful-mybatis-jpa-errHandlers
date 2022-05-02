package br.revisaoBackEnd_restful.revisaoRestful.service;

import br.revisaoBackEnd_restful.revisaoRestful.model.Student;
import br.revisaoBackEnd_restful.revisaoRestful.model.dto.StudentDto;
import org.springframework.data.domain.Page;

public interface StudentService {


    Page<Student> listStudents(int pagina, int tam_pag, String prop);
    StudentDto getStudentWithId(int id);
    StudentDto saveNewStudent(Student student);
    void deleteStudent(int id);
    StudentDto updateDataStudent(int id, Student student);

}
