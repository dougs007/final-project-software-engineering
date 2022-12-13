package com.br.sigaf.domain.dto;

import lombok.*;

import java.util.Set;

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
    private String gender;
    private Integer roleId;
    private Long unityId;
    private UnityDTO unity;
    private Integer userId;
    private Set<AlertDTO> alerts;
}
