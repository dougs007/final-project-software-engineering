package com.br.sigaf.domain.service.impl;

import com.br.sigaf.domain.dto.UserDTO;
import com.br.sigaf.domain.entity.User;
import com.br.sigaf.domain.repository.UserRepository;
import com.br.sigaf.domain.service.AuthService;
import com.br.sigaf.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final AuthService authService;

    @Override
    public User createUser(User usuario) {
        authService.validarEmail(usuario.getEmail());
        authService.criptografarSenha(usuario);
        return repository.save(usuario);
    }

    @Override
    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> usersDTO = new ArrayList<>();

        this.repository.findAll().forEach(f -> {
            UserDTO user = UserDTO.builder()
                    .name(f.getName())
                    .email(f.getEmail())
                    .build();
            usersDTO.add(user);
        });

        return usersDTO;
    }
}
