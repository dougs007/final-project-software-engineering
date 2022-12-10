package com.br.sigaf.api.controller;

import com.br.sigaf.domain.dto.UserDTO;
import com.br.sigaf.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> usersDTO = this.service.getAll();
        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }
}
