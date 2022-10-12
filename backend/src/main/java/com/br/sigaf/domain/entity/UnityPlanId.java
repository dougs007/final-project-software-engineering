package com.br.sigaf.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
public class UnityPlanId implements Serializable {

    @Column(nullable = false)
    private Integer unityId;
    @Column(nullable = false)
    private Integer planId;
}
