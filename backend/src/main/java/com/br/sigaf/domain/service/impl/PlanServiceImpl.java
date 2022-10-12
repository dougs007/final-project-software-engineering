package com.br.sigaf.domain.service.impl;

import com.br.sigaf.domain.dto.PlanDTO;
import com.br.sigaf.domain.dto.UserDTO;
import com.br.sigaf.domain.entity.Plan;
import com.br.sigaf.domain.entity.Unity;
import com.br.sigaf.domain.entity.User;
import com.br.sigaf.domain.enumerates.GenderEnum;
import com.br.sigaf.domain.enumerates.RoleEnum;
import com.br.sigaf.domain.exception.RegraNegocioException;
import com.br.sigaf.domain.repository.PlanRepository;
import com.br.sigaf.domain.repository.UserRepository;
import com.br.sigaf.domain.service.CoachService;
import com.br.sigaf.domain.service.PlanService;
import com.br.sigaf.domain.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository repository;

    public PlanServiceImpl(PlanRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public List<Plan> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Plan save(PlanDTO planDTO) {
        Plan plan = Plan.builder()
                .name(planDTO.getName())
                .description(planDTO.getDescription())
                .value(planDTO.getValue())
                .qtdDaysValidity(planDTO.getQtdDaysValidity())
                .build();

        return this.repository.saveAndFlush(plan);
    }

    @Override
    public Optional<Plan> getById(Long id) {
        return Optional.ofNullable(this.repository.findById(id).orElseThrow(
                () -> new RegraNegocioException("Recurso não encontrado")
        ));
    }

    @Override
    public Plan update(PlanDTO planDTO, Long id) {
        Plan plan = Plan.builder()
                .id(id)
                .name(planDTO.getName())
                .description(planDTO.getDescription())
                .value(planDTO.getValue())
                .qtdDaysValidity(planDTO.getQtdDaysValidity())
                .build();

        return this.repository.saveAndFlush(plan);
    }

    @Override
    public void delete(Long id) {
        try {
            this.repository.deleteById(id);
        } catch (Exception e) {
            throw new RegraNegocioException("Recurso não encontrado");
        }
    }
}
