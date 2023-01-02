package com.br.sigaf.api.controller;

import com.br.sigaf.domain.dto.UserDTO;
import com.br.sigaf.domain.entity.User;
import com.br.sigaf.domain.exception.RegraNegocioException;
import com.br.sigaf.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService service;

    @PostMapping()
    public ResponseEntity<?> createLogin(@RequestBody UserDTO dto) {
        User usuario = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword()).build();

        try {
            User usuarioSalvo = service.createUser(usuario);
            return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
