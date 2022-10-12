package com.br.sigaf.domain.repository;

import com.br.sigaf.domain.entity.Unity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnityRepository extends JpaRepository<Unity, Long> {

    Optional<Unity> findById(Long id);
}
