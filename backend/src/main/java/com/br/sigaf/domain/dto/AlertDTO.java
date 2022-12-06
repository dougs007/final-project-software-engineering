package com.br.sigaf.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlertDTO {
// @todo fix this class to get all data from the entity.
    private Long id;
    private String name;
    private String description;
    private Double value;
    private Integer qtdDaysValidity;
}
