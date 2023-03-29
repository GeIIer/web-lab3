package com.ssau.study.factoryDto;

import com.ssau.study.dto.GroupPojo;
import com.ssau.study.entity.Group;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GroupFactory {

    private final StudentFactory studentFactory;

    public GroupPojo toPojo(Group entity) {
        return GroupPojo.builder()
                .id(entity.getId())
                .name(entity.getName())
                .students(entity.getStudents() != null ? entity.getStudents()
                        .stream()
                        .map(studentFactory::toPojo)
                        .toList() : null)
                .build();
    }

    public Group toEntity(GroupPojo dto) {
        Group group = new Group();
        group.setId(dto.getId());
        group.setName(dto.getName());
        group.setStudents(dto.getStudents() != null ? dto.getStudents()
                .stream()
                .map(studentFactory::toEntity)
                .toList() : null);
        return group;
    }
}
