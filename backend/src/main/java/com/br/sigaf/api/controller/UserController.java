package com.br.sigaf.api.controller;

import com.br.sigaf.domain.dto.TokenDTO;
import com.br.sigaf.domain.dto.UserDTO;
import com.br.sigaf.domain.entity.User;
import com.br.sigaf.domain.exception.AuthenticationError;
import com.br.sigaf.domain.exception.RegraNegocioException;
import com.br.sigaf.domain.service.JwtService;
import com.br.sigaf.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        List<UserDTO> usersDTO = new ArrayList<>();
//        try {
            List<User> users = this.service.getAll();
            users.forEach( f -> {
                UserDTO user = UserDTO.builder()
                        .name(f.getName())
                        .email(f.getEmail())
                        .build();
                usersDTO.add(user);
            });
            return usersDTO;
//        }catch (RegraNegocioException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
    }
}
