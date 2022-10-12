package com.br.sigaf.domain.entity;

import com.br.sigaf.domain.enumerates.GenderEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long      id;

    @Column(nullable = false)
    private String      name;
    private String      celphone;
    private GenderEnum  gender;
    @Column(nullable = false)
    private String      email;
    @Column(nullable = false)
    @JsonIgnore
    private String password;

}
