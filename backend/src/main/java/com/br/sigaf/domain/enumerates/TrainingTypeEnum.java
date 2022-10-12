package com.br.sigaf.domain.enumerates;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
@Getter
public enum TrainingTypeEnum {
    BOTTOM(1, "Bottom", "Inferior"),
    SUPERIOR(2, "Superior", "Superior"),
    HINDQUARTER(3, "Hindquarter", "Posterior"),
    PREVIOUS(4, "Previous", "Anterior");

    private final Integer code;
    private final String  nameEn;
    private final String  namePt;

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
