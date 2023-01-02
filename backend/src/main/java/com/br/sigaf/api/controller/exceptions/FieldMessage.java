package com.br.sigaf.api.controller.exceptions;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FieldMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fieldName;
    private String message;
}
