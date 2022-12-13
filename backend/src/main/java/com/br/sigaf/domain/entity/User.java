package com.br.sigaf.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String celphone;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    //    @Column(columnDefinition = "default " + GenderEnum.OTHERS.getCode())
    @Column(columnDefinition = "integer default " + 1)
    private Integer genderId;
    //    @Column(columnDefinition = "default " + RoleEnum.ROLE_STUDENT.getCode())
    @Column(columnDefinition = "integer default " + 3)
    private Integer roleId;
    private Long userId;

    private Long unityId;

    @JsonManagedReference
    @OneToMany(mappedBy="student", fetch = FetchType.LAZY)
    private List<Alert> alerts;
}
