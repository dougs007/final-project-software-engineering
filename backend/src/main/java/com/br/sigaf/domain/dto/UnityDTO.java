package com.br.sigaf.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnityDTO {

    private Long id;
    private String name;
    private String state;
    private String city;
    private Long postalCode;
}
