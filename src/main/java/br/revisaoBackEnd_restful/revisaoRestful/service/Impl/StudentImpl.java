package br.revisaoBackEnd_restful.revisaoRestful.service.Impl;

import br.revisaoBackEnd_restful.revisaoRestful.model.Student;
import br.revisaoBackEnd_restful.revisaoRestful.model.dto.StudentDto;
import br.revisaoBackEnd_restful.revisaoRestful.repository.StudentRepository;
import br.revisaoBackEnd_restful.revisaoRestful.repository.mapper.StudentMapper;
import br.revisaoBackEnd_restful.revisaoRestful.service.StudentService;
import br.revisaoBackEnd_restful.revisaoRestful.service.exception.ObjectNotFoundError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentImpl implements StudentService, StudentMapper {

    @Autowired
    StudentRepository repository;

    @Autowired
    StudentMapper mapper;


    @Override
    public StudentDto getStudentWithId(int id) {
        Optional<Student> byId = this.repository.findById(id);
        return byId.map(x -> new StudentDto(byId.get()))
                .orElseThrow(() -> new ObjectNotFoundError("student not found"));
    }

    @Override
    public Page<Student> listStudents(int pagina, int tam_pag, String prop) {
        Pageable pag = PageRequest.of(pagina, tam_pag, Sort.by(prop).descending());
        return this.repository.findAll(pag);
    }

    private boolean checkedExistentStudent(int id){
        Optional<Student> byId = this.repository.findById(id);
        if(byId.isPresent()){
            return true;
        }
        throw new ObjectNotFoundError("student doesn't exist");
    }

    protected void validateNoRegistry(Student user){
        Optional<Student> str = this.repository.findByRegistry(user.getRegistry());
        if(str.isPresent()){
            throw new ObjectNotFoundError("error no. registry");
        }
    }

    @Override
    public StudentDto saveNewStudent(Student student) {

        this.validateNoRegistry(student);
        Student std = this.repository.save(student);
        return new StudentDto(std);

    }

    @Override
    public void deleteStudent(int id) {
        if(this.checkedExistentStudent(id)){
            this.repository.deleteById(id);
        }
    }

    @Override
    public StudentDto updateDataStudent(int id, Student student) {
        Optional<Student> byId = this.repository.findById(id);
        if(byId.isPresent()){
            this.validateNoRegistry(student);
            byId.get().setName(student.getName());
            return new StudentDto(this.repository.save(byId.get()));
        }
        throw new RuntimeException("ops...");
    }

    @Override
    public List<Student> getAllStudent() {
        return this.mapper.getAllStudent();
    }
}
