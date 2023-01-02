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
@Table(name = "tb_unity_plan")
public class UnityPlan {

    @EmbeddedId
    private UnityPlanId id;

    @ManyToOne
    @MapsId("unityId")
    @JoinColumn(name = "unity_id")
    private Unity unity;

    @ManyToOne
    @MapsId("planId")
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Column(nullable = false)
    private LocalDate dateCreated;
}
