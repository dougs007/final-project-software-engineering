package com.br.sigaf.domain.repository;

import com.br.sigaf.domain.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

    @Query("from Alert a WHERE a.readingConfirmation = false and a.userId = :userId")
    List<Alert> findAllByUserId(Integer userId);
}
