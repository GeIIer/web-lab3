package com.ssau.study.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class StudentPojo {
    private Long id;
    private String firstname;
    private String lastname;
    private Date birthdate;
    private int number;
    private Long groupId;

}
