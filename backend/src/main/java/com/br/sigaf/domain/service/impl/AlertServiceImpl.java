package com.br.sigaf.domain.service.impl;

import com.br.sigaf.domain.dto.AlertDTO;
import com.br.sigaf.domain.entity.Alert;
import com.br.sigaf.domain.repository.AlertRepository;
import com.br.sigaf.domain.service.AlertService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlertServiceImpl implements AlertService {

    private final AlertRepository repository;

    @Override
    public List<AlertDTO> getAllByUserId(Integer userId) {
        List<Alert> alerts = repository.findAllByUserId(userId);
//        return alerts.forEach(al -> );
        return null;
    }

    @Override
    public AlertDTO save(AlertDTO userDTO) {
        return null;
    }

    @Override
    public Optional<AlertDTO> getById(Long id) {
        return Optional.empty();
    }
}
