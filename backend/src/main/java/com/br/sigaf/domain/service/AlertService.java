package com.br.sigaf.domain.service;


import com.br.sigaf.domain.dto.AlertDTO;

import java.util.List;

public interface AlertService {

    List<AlertDTO> getAllByUserId(Long userId);
    AlertDTO create(AlertDTO userDTO);

    AlertDTO markAlertAsRead(Long alertId);
}
