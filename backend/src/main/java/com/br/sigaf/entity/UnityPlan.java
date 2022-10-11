package com.br.sigaf.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class UnityPlan {

    @EmbeddedId
    private UnityPlanId id;

    @Column(nullable = false)
    private LocalDate dateCreated;
}
