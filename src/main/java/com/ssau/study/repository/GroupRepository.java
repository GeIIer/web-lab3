package com.ssau.study.repository;

import com.ssau.study.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> findByNameContainingIgnoreCase(String name);

    Optional<Group> findByName(String name);

    List<Group> findAll();

    void deleteById(Long id);
}