package com.br.sigaf.api.controller;

import com.br.sigaf.domain.dto.AlertDTO;
import com.br.sigaf.domain.service.AlertService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@AllArgsConstructor
public class AlertController {

    private final AlertService service;

    @GetMapping("/user/{userId}")
    public List<AlertDTO> getAllByUserId(@PathVariable Long userId) {
        return this.service.getAllByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<AlertDTO> createAlert(@RequestBody AlertDTO dto) {
        AlertDTO entityDto = service.create(dto);
        return new ResponseEntity<>(entityDto, HttpStatus.CREATED);
    }

    @PostMapping("/{alertId}")
    public ResponseEntity<AlertDTO> markAlertAsRead(@PathVariable Long alertId) {
        AlertDTO alertDto = service.markAlertAsRead(alertId);
        return new ResponseEntity<>(alertDto, HttpStatus.OK);
    }
}
