package com.br.sigaf.domain.service;


import com.br.sigaf.domain.dto.UnityDTO;
import com.br.sigaf.domain.entity.Unity;

import java.util.List;
import java.util.Optional;

public interface UnityService {

    List<Unity> getAll();
    Unity save(UnityDTO dto);
    Optional<Unity> getById(Long id);
    Unity update(UnityDTO dto, Long id);
    void delete(Long id);
}

