package com.ssau.study.factoryDto;

import com.ssau.study.dto.StudentPojo;
import com.ssau.study.entity.Student;
import com.ssau.study.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudentFactory {

    private final GroupRepository groupRepository;

    public StudentPojo toPojo(Student entity) {
        return StudentPojo.builder()
                .id(entity.getId())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .birthdate(entity.getBirthdate())
                .number(entity.getNumber())
                .groupId(entity.getGroup() != null ? entity.getGroup().getId() : null)
                .build();
    }

    public Student toEntity(StudentPojo dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setFirstname(dto.getFirstname());
        student.setLastname(dto.getLastname());
        student.setBirthdate(dto.getBirthdate());
        student.setNumber(dto.getNumber());
        if (dto.getGroupId() != null) {
            student.setGroup(groupRepository.findById(dto.getGroupId()).orElse(null));
        }
        return student;
    }
}
