package com.br.sigaf.enumerates;

public enum TrainingTypeEnum {
    BOTTOM(1, "Bottom", "Inferior"),
    SUPERIOR(2, "Superior", "Superior"),
    HINDQUARTER(3, "Hindquarter", "Posterior"),
    PREVIOUS(4, "Previous", "Anterior");

    private final Integer code;
    private final String  nameEn;
    private final String  namePt;

    TrainingTypeEnum(Integer code, String nameEn, String namePt) {
        this.code = code;
        this.nameEn = nameEn;
        this.namePt = namePt;
    }
}
