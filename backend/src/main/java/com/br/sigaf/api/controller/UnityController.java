package com.br.sigaf.api.controller;

import com.br.sigaf.domain.dto.UnityDTO;
import com.br.sigaf.domain.entity.Unity;
import com.br.sigaf.domain.service.UnityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/units")
@AllArgsConstructor
public class UnityController {

    private final UnityService service;

    @GetMapping
    public List<Unity> getAll() {
        return this.service.getAll();
    }

    @PostMapping
    public ResponseEntity<Unity> save(@RequestBody UnityDTO dto) {
        Unity unity = service.save(dto);
        return new ResponseEntity<>(unity, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unity> getById(@PathVariable Long id) {
        Optional<Unity> unity = service.getById(id);
        return new ResponseEntity<>(unity.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unity> update(@RequestBody UnityDTO dto, @PathVariable Long id) {
        Unity unity = service.update(dto, id);
        return new ResponseEntity<>(unity, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
