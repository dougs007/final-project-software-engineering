package com.br.sigaf.domain.service;


import com.br.sigaf.domain.dto.AlertDTO;
import com.br.sigaf.domain.entity.Alert;

import java.util.List;
import java.util.Optional;

public interface AlertService {

    List<AlertDTO> getAllByUserId(Integer userId);
    AlertDTO save(AlertDTO userDTO);
    Optional<AlertDTO> getById(Long id);

}

