package br.revisaoBackEnd_restful.revisaoRestful.service.Impl;

import br.revisaoBackEnd_restful.revisaoRestful.model.Student;
import br.revisaoBackEnd_restful.revisaoRestful.model.dto.StudentDto;
import br.revisaoBackEnd_restful.revisaoRestful.repository.StudentRepository;
import br.revisaoBackEnd_restful.revisaoRestful.repository.mapper.StudentMapper;
import br.revisaoBackEnd_restful.revisaoRestful.service.exception.ObjectNotFoundError;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class StudentImplTest {

    public static final int ID = 1;
    public static final String NAME = "pedro joaquim";
    public static final String REGISTRY = "212051";
    public static final String STUDENT_NOT_FOUND = "student not found";
    @InjectMocks
    StudentImpl impl;

    @Mock
    StudentRepository repository;

    @Mock
    StudentMapper mapper;

    List<StudentDto> listStudentDto;
    Optional<Student> optionalStudent;
    List<Student> listStudent;
    StudentDto modelStudentDto;
    Page<Student> page_student;
    Student modelStudent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.initializerComponents();
    }

    @Test
    @DisplayName("testando o metodo do impl de listar todos os estudantes")
    void listStudents() {
        Mockito.when(this.impl.listStudents(1,1,"nome")).thenReturn(page_student);
    }

    @Test
    void getStudentWithId() {

        Mockito.when(this.repository.findById(Mockito.anyInt())).thenReturn(optionalStudent);

        StudentDto dto = this.impl.getStudentWithId(ID);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(NAME,dto.getName());
        Assertions.assertEquals(ID,dto.getId());

    }

    @Test
    void getThrowExceptionWhenGetStudentWithIdIsCalling(){
        try{
            Mockito.when(this.impl.getStudentWithId(Mockito.anyInt())).thenThrow(new ObjectNotFoundError(STUDENT_NOT_FOUND));
        }
        catch (ObjectNotFoundError ex){
            Assertions.assertEquals(ObjectNotFoundError.class,ex.getClass());
            Assertions.assertEquals(STUDENT_NOT_FOUND,ex.getMessage());
        }
    }

    @Test
    void saveNewStudent() {

        Mockito.when(this.repository.save(modelStudent)).thenReturn(modelStudent);

        this.impl.saveNewStudent(modelStudent);

        try {
            Mockito.doThrow(new ObjectNotFoundError("error no. registry")).doNothing();
        }
        catch (ObjectNotFoundError ex){
            Assertions.assertEquals(ObjectNotFoundError.class,ex.getClass());
            Assertions.assertEquals(ex.getMessage(),"error no. registry");
        }

    }

    @Test
    void checkedExistentStudent(){
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.of(modelStudent));
        Assertions.assertEquals(Optional.of(modelStudent).isPresent(),true);
        try{
            Mockito.when(this.impl.checkedExistentStudent(ID))
                    .thenThrow(new ObjectNotFoundError("student doesn't exist"));
        }catch (ObjectNotFoundError ex){
            Assertions.assertEquals(ObjectNotFoundError.class,ex.getClass());
            Assertions.assertEquals("student doesn't exist",ex.getMessage());
        }
    }

    @Test
    void deleteStudent() {
        Mockito.doNothing().when(this.repository).deleteById(ID);
        this.impl.deleteStudent(modelStudent.getId());
        Mockito.verify(repository,Mockito.times(1)).deleteById(ID);
    }

    @Test
    void updateDataStudent() {
        Mockito.when(this.repository.findById(Mockito.anyInt())).thenReturn(optionalStudent);

        Mockito.when(this.repository.save(modelStudent)).thenReturn(modelStudent);

        StudentDto dto = this.impl.updateDataStudent(ID,modelStudent);
    }

    @Test
    void getAllStudent() {
        Mockito.when(this.mapper.getAllStudent()).thenReturn(listStudent);
        List<Student> std = this.impl.getAllStudent();
        Assertions.assertNotNull(std);
    }

    private void initializerComponents(){
        modelStudent = new Student(ID, NAME, REGISTRY);
        modelStudentDto = new StudentDto(modelStudent);
        optionalStudent = Optional.of(modelStudent);
        page_student = Mockito.mock(Page.class);
        listStudent = List.of(modelStudent);
        listStudentDto = List.of(modelStudentDto);
    }
}