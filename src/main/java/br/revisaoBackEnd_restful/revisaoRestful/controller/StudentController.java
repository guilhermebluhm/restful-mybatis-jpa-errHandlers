package br.revisaoBackEnd_restful.revisaoRestful.controller;

import br.revisaoBackEnd_restful.revisaoRestful.model.Student;
import br.revisaoBackEnd_restful.revisaoRestful.model.dto.StudentDto;
import br.revisaoBackEnd_restful.revisaoRestful.service.Impl.StudentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/")
public class StudentController {

    @Autowired
    private StudentImpl impl;

    @GetMapping("list-all-no-dto")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAllNoDto(){
        return this.impl.getAllStudent();
    }

    @GetMapping("list-all-using-pagination")
    @ResponseStatus(HttpStatus.OK)
    public Page<Student> getAllWithPagination(
            @RequestParam(required = false, defaultValue = "0") int pagina,
            @RequestParam(required = false, defaultValue = "1") int tamanhoPagina,
            @RequestParam(required = false, defaultValue = "name") String ord){
        return this.impl.listStudents(pagina, tamanhoPagina, ord);
    }

    @GetMapping("list-all-using-dto/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDto getAllUsingDto(@PathVariable int id){
        return this.impl.getStudentWithId(id);
    }

    @PostMapping("new-student")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDto createNewStudent(@RequestBody Student student){
        return this.impl.saveNewStudent(student);
    }

    @DeleteMapping("delete-student/{id}")
    public void deleteStudent(@PathVariable int id){
        this.impl.deleteStudent(id);
    }

    @PutMapping("update-student/{id}")
    public StudentDto updateDataStudent(@PathVariable int id, @RequestBody Student student){
        return this.impl.updateDataStudent(id,student);
    }
}
