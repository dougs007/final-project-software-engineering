package com.br.sigaf.domain.service.impl;

import com.br.sigaf.domain.dto.AlertDTO;
import com.br.sigaf.domain.entity.Alert;
import com.br.sigaf.domain.exception.ResourceNotFoundException;
import com.br.sigaf.domain.repository.AlertRepository;
import com.br.sigaf.domain.service.AlertService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlertServiceImpl implements AlertService {

    private final AlertRepository repository;

    @Override
    public List<AlertDTO> getAllByUserId(Long userId) {
        List<Alert> alerts = repository.findAllByUserId(userId);

        return alerts.stream()
                .map(AlertDTO::parse).collect(Collectors.toList());
    }

    @Override
    public AlertDTO create(AlertDTO dto) {
        Alert entity = Alert.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .showDate(dto.getShowDate())
                .readingConfirmation(dto.getReadingConfirmation())
//                .userId(dto.getUserId())
                // @todo resolver esse bug
                .build();

        entity = repository.save(entity);

        return AlertDTO.parse(entity);
    }

    @Override
    public AlertDTO markAlertAsRead(Long alertId) {
        Alert entity = repository.findById(alertId).orElseThrow(
                () -> new ResourceNotFoundException("Alert Id not found " + alertId)
        );

        entity.setReadingConfirmation(true);

        entity = repository.save(entity);
        return AlertDTO.parse(entity);
    }
}
