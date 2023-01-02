package com.br.sigaf.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_coupon")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String description;
    private LocalDate dateCreated;
    @Column(nullable = false, columnDefinition = "false")
    private Boolean isValid;

    @JsonManagedReference
    @OneToMany(mappedBy="coupon", fetch = FetchType.LAZY)
    private List<Signature> signatures;
}
