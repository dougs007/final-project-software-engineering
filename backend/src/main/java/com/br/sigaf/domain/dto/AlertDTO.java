package com.br.sigaf.domain.dto;

import com.br.sigaf.domain.entity.Alert;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlertDTO implements Serializable {
    private Long id;
    private String name;
    private String description;
    private LocalDate showDate;
    private Boolean readingConfirmation;
    private Long userId;

    public static AlertDTO parse(Alert entity) {
        return AlertDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .showDate(entity.getShowDate())
                .readingConfirmation(entity.getReadingConfirmation())
                .userId(entity.getUserId())
                .build();
    }
}
