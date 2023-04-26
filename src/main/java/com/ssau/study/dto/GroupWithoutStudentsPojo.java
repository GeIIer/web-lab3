package com.ssau.study.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GroupWithoutStudentsPojo {
    private Long id;
    private String name;
}
