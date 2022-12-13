package com.br.sigaf.domain.dto;

import com.br.sigaf.domain.entity.Unity;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnityDTO {

    private Long id;
    private String name;
    private String state;
    private String city;
    private Long postalCode;

    public static UnityDTO parse(Unity unity) {
        if (null == unity) {
            return null;
        }
        return UnityDTO.builder()
                .id(unity.getId())
                .name(unity.getName())
                .build();
    }
}
