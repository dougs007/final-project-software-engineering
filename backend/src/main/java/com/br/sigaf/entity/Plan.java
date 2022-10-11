package com.br.sigaf.entity;

import com.br.sigaf.enumerates.GenderEnum;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Data
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column(nullable = false)
    private Double value;
    @Column(nullable = false)
    private Integer qtdDaysValidity;
}
