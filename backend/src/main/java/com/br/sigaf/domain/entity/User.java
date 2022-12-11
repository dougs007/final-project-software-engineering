package com.br.sigaf.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String celphone;

    //    @Column(columnDefinition = "default " + GenderEnum.OTHERS.getCode())
    @Column(columnDefinition = "integer default " + 1)
    private Integer genderId;
    //    @Column(columnDefinition = "default " + RoleEnum.ROLE_STUDENT.getCode())
    @Column(columnDefinition = "integer default " + 3)
    private Integer roleId;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    private Integer userId;

    private Integer unityId;

    @OneToMany(mappedBy="student")
    private Set<Alert> alerts;
}
