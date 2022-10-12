package com.br.sigaf.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnityPlan {

    @EmbeddedId
    private UnityPlanId id;

    @Column(nullable = false)
    private LocalDate dateCreated;
}
