package com.br.sigaf.domain.repository;

import com.br.sigaf.domain.entity.Unity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnityRepository extends JpaRepository<Unity, Long> {

    Optional<Unity> findById(Long id);
}
