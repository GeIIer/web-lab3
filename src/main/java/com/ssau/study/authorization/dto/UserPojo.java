package com.ssau.study.authorization.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPojo {

    private Long id;

    private String name;

    private String email;

    private String role;
}
