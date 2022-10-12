package com.br.sigaf.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanDTO {

    private Long id;
    private String name;
    private String description;
    private Double value;
    private Integer qtdDaysValidity;
}
