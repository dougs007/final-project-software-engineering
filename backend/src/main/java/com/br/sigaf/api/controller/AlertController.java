package com.br.sigaf.api.controller;

import com.br.sigaf.domain.dto.AlertDTO;
import com.br.sigaf.domain.entity.Alert;
import com.br.sigaf.domain.service.AlertService;
import com.br.sigaf.domain.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService service;

    @GetMapping("/user/{userId}")
    public List<AlertDTO> getAllByUserId(@PathVariable Integer userId) {
        return this.service.getAllByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<Alert> createAlert(@RequestBody AlertDTO dto) {
        Alert alert = service.save(dto);
        return new ResponseEntity<>(alert, HttpStatus.CREATED);
    }

    @PostMapping("/{alertId}")
    public ResponseEntity<Alert> markAlertAsRead(@PathVariable Long alertId) {
        return new ResponseEntity<>(alert.get(), HttpStatus.OK);
    }

}
