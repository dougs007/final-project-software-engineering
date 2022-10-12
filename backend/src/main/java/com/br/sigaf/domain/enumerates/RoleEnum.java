package com.br.sigaf.domain.enumerates;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
@Getter
public enum RoleEnum {
    ROLE_ADMIN(1, "Admin", "Administrador"),
    ROLE_COACH(2, "Coach", "Personal"),
    ROLE_STUDENT(3, "Student", "Aluno");

    private final Integer code;
    private final String nameEn;
    private final String namePt;

    @JsonValue
    public Integer getCode() {
        return this.code;
    }

    @JsonCreator
    public static GenderEnum toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (GenderEnum serie : GenderEnum.values()) {
            if (code.equals(serie.getCode())) {
                return serie;
            }
        }
        throw new IllegalArgumentException("Invalid Code: " + code);
    }
}
