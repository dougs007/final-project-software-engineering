package com.br.sigaf.domain.service.impl;

import com.br.sigaf.domain.dto.UnityDTO;
import com.br.sigaf.domain.entity.Unity;
import com.br.sigaf.domain.exception.RegraNegocioException;
import com.br.sigaf.domain.repository.UnityRepository;
import com.br.sigaf.domain.service.UnityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UnityServiceImpl implements UnityService {

    private final UnityRepository repository;

    @Override
    public List<Unity> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Unity save(UnityDTO dto) {
        Unity unity = Unity.builder()
                .name(dto.getName())
                .city(dto.getCity())
                .state(dto.getState())
                .postalCode(dto.getPostalCode())
                .build();

        return this.repository.saveAndFlush(unity);
    }

    @Override
    public Optional<Unity> getById(Long id) {
        return Optional.ofNullable(this.repository.findById(id).orElseThrow(
                () -> new RegraNegocioException("Recurso não encontrado")
        ));
    }

    @Override
    public Unity update(UnityDTO dto, Long id) {
        Unity unity = Unity.builder()
            .id(id)
            .name(dto.getName())
            .city(dto.getCity())
            .state(dto.getState())
            .postalCode(dto.getPostalCode())
            .build();
        return this.repository.saveAndFlush(unity);
    }


    @Transactional
    @Override
    public void delete(Long id) {
        try {
            this.repository.deleteById(id);
        } catch (Exception e) {
            throw new RegraNegocioException("Não foi possível remover a unidade");
        }
    }
}
