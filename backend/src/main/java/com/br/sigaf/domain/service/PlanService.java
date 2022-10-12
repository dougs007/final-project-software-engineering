package com.br.sigaf.domain.service;


import com.br.sigaf.domain.dto.PlanDTO;
import com.br.sigaf.domain.dto.UserDTO;
import com.br.sigaf.domain.entity.Plan;
import com.br.sigaf.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface PlanService {

    List<Plan> getAll();
    Plan save(PlanDTO userDTO);
    Optional<Plan> getById(Long id);
    Plan update(PlanDTO dto, Long id);
    void delete(Long id);
}

