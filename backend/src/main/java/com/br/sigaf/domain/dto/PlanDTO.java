package com.br.sigaf.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanDTO {

    private Long id;
    private String name;
    private String description;
    private Double valuePlan;
    private Integer qtdDaysValidity;
}
