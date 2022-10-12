package com.br.sigaf.domain.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long    id;
    private String  name;
    private String  celphone;
    private String  email;
    private String  password;
    private Integer genderId;
    private Integer roleId;
    private Integer userId;
}
