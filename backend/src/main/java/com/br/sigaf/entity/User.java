package com.br.sigaf.entity;

import com.br.sigaf.enumerates.GenderEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long      id;

    private String      name;
    private String      celphone;
    private GenderEnum  gender;
    private String      email;
}
