package com.ssau.study.controller;

import com.ssau.study.dto.StudentPojo;
import com.ssau.study.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/count")
    public long count() {
        return studentService.count();
    }

    @GetMapping("/all")
    public List<StudentPojo> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/all/{name}")
    public List<StudentPojo> findAllByName(@PathVariable String name) {
        return studentService.findAllByName(name);
    }

    @PostMapping()
    public StudentPojo createStudent(@RequestBody StudentPojo studentPojo) {
        return studentService.createStudent(studentPojo);
    }

    @PutMapping()
    public StudentPojo updateStudent(@RequestBody StudentPojo dto) {
        return studentService.update(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
    }

    @GetMapping("/{id}")
    public StudentPojo findStudentById(@PathVariable long id) {
        return studentService.findById(id);
    }
}
