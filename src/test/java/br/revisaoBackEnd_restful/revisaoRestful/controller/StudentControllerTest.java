package br.revisaoBackEnd_restful.revisaoRestful.controller;

import br.revisaoBackEnd_restful.revisaoRestful.model.Student;
import br.revisaoBackEnd_restful.revisaoRestful.model.dto.StudentDto;
import br.revisaoBackEnd_restful.revisaoRestful.repository.mapper.StudentMapper;
import br.revisaoBackEnd_restful.revisaoRestful.service.Impl.StudentImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import java.util.List;

class StudentControllerTest {

    public static final int ID = 1;
    public static final String NAME = "maria lionete";
    public static final String REGISTRY = "236597";

    @InjectMocks
    StudentController controller;

    @Mock
    StudentImpl impl;

    @Mock
    StudentMapper mapper;

    Student student;
    StudentDto dto;
    Page<Student> p_std;
    List<Student> l_student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.initializerModels();
    }

    @Test
    void getAllNoDto() {
        Mockito.when(this.mapper.getAllStudent()).thenReturn(l_student);
        l_student = this.controller.getAllNoDto();
        l_student.add(new Student(ID,NAME,REGISTRY));
        Assertions.assertEquals(l_student.get(0).getId(),ID);
        Assertions.assertEquals(l_student.get(0).getName(),NAME);
    }

    @Test
    void getAllWithPagination() {

    }

    @Test
    void getAllUsingDto() {
        dto = this.controller.getAllUsingDto(ID);
    }

    @Test
    void createNewStudent() {
        dto = this.controller.createNewStudent(student);
    }

    @Test
    void deleteStudent() {
        this.controller.deleteStudent(ID);
        Mockito.verify(this.impl,Mockito.times(1)).deleteStudent(ID);
    }

    @Test
    void updateDataStudent() {
    }

    private void initializerModels(){
        student = new Student(ID, NAME, REGISTRY);
        dto = new StudentDto(student);
        l_student = List.of(student);
        p_std = Mockito.mock(Page.class);
    }
}