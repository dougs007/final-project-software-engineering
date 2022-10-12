package com.br.sigaf.domain.enumerates;

public enum GenderEnum {
    MALE(1, "Male", "Masculino"),
    FEMALE(2, "Female", "Feminino"),
    OTHERS(3, "Others", "Outros");

    private final Integer code;
    private final String  nameEn;
    private final String  namePt;

    GenderEnum(Integer code, String nameEn, String namePt) {
        this.code = code;
        this.nameEn = nameEn;
        this.namePt = namePt;
    }
}
