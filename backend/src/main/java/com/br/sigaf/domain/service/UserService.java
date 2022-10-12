package com.br.sigaf.domain.service;


import com.br.sigaf.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User autenticar(String email, String senha);

    User salvarUsuario(User usuario);

    void validarEmail(String email);

    Optional<User> obterPorId(Long id);

    List<User> getAll();
}
