package com.br.sigaf.api.controller;

import com.br.sigaf.domain.dto.UserDTO;
import com.br.sigaf.domain.entity.User;
import com.br.sigaf.domain.service.CoachService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/coaches")
@AllArgsConstructor
public class CoachController {

    private final CoachService service;

    @GetMapping
    public List<UserDTO> getAll() {
        return this.service.getAll();
    }

    @PostMapping
    public ResponseEntity<User> createCoach(@RequestBody UserDTO dto) {
        User coach = service.createCoach(dto);
        return new ResponseEntity<>(coach, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        UserDTO coach = service.getCoachById(id);
        return new ResponseEntity<>(coach, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody UserDTO dto, @PathVariable Long id) {
        User coach = service.update(dto, id);
        return new ResponseEntity<>(coach, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
