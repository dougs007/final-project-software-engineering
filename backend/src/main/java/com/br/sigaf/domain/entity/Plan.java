package com.br.sigaf.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column(nullable = false)
    private Double value;
    @Column(nullable = false)
    private Integer qtdDaysValidity;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", insertable = false, updatable = false)
//    @JsonBackReference
//    private User student;

    @JsonManagedReference
    @OneToMany(mappedBy="plan", fetch = FetchType.LAZY)
    private List<Signature> signatures;
}
