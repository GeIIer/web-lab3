package com.ssau.study.service;

import com.ssau.study.dto.GroupPojo;
import com.ssau.study.dto.GroupWithoutStudentsPojo;
import com.ssau.study.entity.Group;
import com.ssau.study.exceptions.GroupExistsException;
import com.ssau.study.exceptions.GroupNotFoundException;
import com.ssau.study.factoryDto.GroupFactory;
import com.ssau.study.factoryDto.StudentFactory;
import com.ssau.study.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    private final GroupFactory groupFactory;

    private final StudentFactory studentFactory;


    public List<GroupWithoutStudentsPojo> findAll() {
        return groupRepository.findAll()
                .stream()
                .map(groupFactory::toWithoutStudentsPojo)
                .toList();
    }

    public GroupPojo findById(Long id) {
        Optional<Group> group = groupRepository.findById(id);
        return group.map(groupFactory::toPojo).orElseThrow(
                () -> new GroupNotFoundException(id.toString()));
    }

    public GroupPojo createGroup(GroupPojo dto) {
        if (groupRepository.findByNameIgnoreCase(dto.getName()).isPresent()) {
            throw new GroupExistsException(dto.getName());
        }
        Group group = groupFactory.toEntity(dto);
        return groupFactory.toPojo(groupRepository.save(group));
    }

    public GroupPojo updateGroup(GroupPojo dto) {
        if (groupRepository.findByNameIgnoreCase(dto.getName()).isPresent()) {
            throw new GroupExistsException(dto.getName());
        }
        return groupFactory.toPojo(groupRepository.save(groupFactory.toEntity(dto)));
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}
