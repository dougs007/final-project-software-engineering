package com.br.sigaf.domain.repository;

import com.br.sigaf.domain.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    Optional<Plan> findById(Long id);
}