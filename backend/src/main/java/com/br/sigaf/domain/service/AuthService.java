package com.br.sigaf.domain.service;

import com.br.sigaf.domain.entity.User;

public interface AuthService {

    User autenticar(String email, String senha);

    void validarEmail(String email);

    void criptografarSenha(User usuario);
}
