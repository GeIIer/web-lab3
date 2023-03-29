package com.ssau.study.controller;

import com.ssau.study.dto.GroupPojo;
import com.ssau.study.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@AllArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/all")
    public List<GroupPojo> findAll() {
        return groupService.findAll();
    }

    @GetMapping("/all/{name}")
    public GroupPojo findByName(@PathVariable String name) {
        return groupService.findByName(name);
    }

    @GetMapping("/{id}")
    public GroupPojo findById(@PathVariable Long id) {
        return groupService.findById(id);
    }

    @PostMapping()
    public GroupPojo createGroup(@RequestBody GroupPojo dto) {
        return groupService.createGroup(dto);
    }

    @PutMapping()
    public GroupPojo updateGroup(@RequestBody GroupPojo dto) {
        return groupService.updateGroup(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }
}
