package com.ssau.study.repository;

import com.ssau.study.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    long count();

    List<Student> findAllByName(String name);

    List<Student> findAll();

    Optional<Student> findById(Long id);

    void deleteById(Long id);
}
