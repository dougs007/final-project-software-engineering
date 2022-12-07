package com.br.sigaf.domain.exception;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class StandardError implements Serializable {

    private LocalDate timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
