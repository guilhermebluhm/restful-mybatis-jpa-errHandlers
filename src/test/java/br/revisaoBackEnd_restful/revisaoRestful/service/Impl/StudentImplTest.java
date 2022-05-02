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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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
    Student modelStudent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.initializerComponents();
    }

    //TODO:RESOLVER O PROBLEMA DO NPE NO CONTEXTO DO PAGEABLE
    @Test
    @DisplayName("testando o metodo do impl de listar todos os estudantes")
    void listAllStudents() {

        //Mockito.when(this.impl.listStudents(p_student)).thenReturn(listStudentDto);

    }

    @Test
    void getStudentWithId() {

        Mockito.when(this.repository.findById(Mockito.anyInt())).thenReturn(optionalStudent);

        StudentDto dto = this.impl.getStudentWithId(Mockito.anyInt());

        Assertions.assertNotNull(dto);

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
    void deleteStudent() {

    }

    @Test
    void updateDataStudent() {
    }

    @Test
    void getAllStudent() {
    }

    private void initializerComponents(){
        modelStudent = new Student(ID, NAME, REGISTRY);
        modelStudentDto = new StudentDto(modelStudent);
        optionalStudent = Optional.of(modelStudent);
        listStudent = List.of(modelStudent);
        listStudentDto = List.of(modelStudentDto);
    }
}