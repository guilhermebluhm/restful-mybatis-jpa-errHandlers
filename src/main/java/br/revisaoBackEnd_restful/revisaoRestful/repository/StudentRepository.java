package br.revisaoBackEnd_restful.revisaoRestful.repository;

import br.revisaoBackEnd_restful.revisaoRestful.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByRegistry(String registry);
}
