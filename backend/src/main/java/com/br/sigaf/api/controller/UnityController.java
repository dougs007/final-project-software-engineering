package com.br.sigaf.api.controller;

import com.br.sigaf.domain.dto.UnityDTO;
import com.br.sigaf.domain.entity.Unity;
import com.br.sigaf.domain.service.UnityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/units")
@RequiredArgsConstructor
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
