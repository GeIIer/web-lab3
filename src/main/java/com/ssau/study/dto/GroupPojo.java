package com.ssau.study.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GroupPojo {
    private long id;
    private String name;
    private List<StudentPojo> students;
}
