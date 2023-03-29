package com.ssau.study.service;

import com.ssau.study.dto.StudentPojo;
import com.ssau.study.entity.Student;
import com.ssau.study.factoryDto.StudentFactory;
import com.ssau.study.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentFactory studentFactory;

    private final StudentRepository studentRepository;

    public StudentPojo createStudent(StudentPojo studentPojo) {
        Student student = studentFactory.toEntity(studentPojo);
        return studentFactory.toPojo(studentRepository.save(student));
    }

    public long count() {
        return studentRepository.count();
    }

    public List<StudentPojo> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(studentFactory::toPojo)
                .toList();
    }

    public List<StudentPojo> findAllByName(String name) {
        return studentRepository.findAllByName(name)
                .stream()
                .map(studentFactory::toPojo)
                .toList();
    }

    public StudentPojo update(StudentPojo dto) {
        return studentFactory.toPojo(studentRepository.save(studentFactory.toEntity(dto)));
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public StudentPojo findById(long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        return studentOptional.map(studentFactory::toPojo).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Студента нет"));
    }
}
