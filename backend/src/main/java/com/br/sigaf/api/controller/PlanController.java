package com.br.sigaf.api.controller;

import com.br.sigaf.domain.dto.PlanDTO;
import com.br.sigaf.domain.entity.Plan;
import com.br.sigaf.domain.service.PlanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plans")
@AllArgsConstructor
public class PlanController {

    private final PlanService service;

    @GetMapping
    public List<Plan> getAll() {
        return this.service.getAll();
    }

    @PostMapping
    public ResponseEntity<Plan> save(@RequestBody PlanDTO dto) {
        Plan plan = service.save(dto);
        return new ResponseEntity<>(plan, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> getById(@PathVariable Long id) {
        Optional<Plan> plan = service.getById(id);
        return new ResponseEntity<>(plan.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plan> update(@RequestBody PlanDTO dto, @PathVariable Long id) {
        Plan plan = service.update(dto, id);
        return new ResponseEntity<>(plan, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
