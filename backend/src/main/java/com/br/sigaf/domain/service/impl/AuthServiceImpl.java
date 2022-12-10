package com.br.sigaf.domain.service.impl;

import com.br.sigaf.domain.entity.User;
import com.br.sigaf.domain.exception.AuthenticationError;
import com.br.sigaf.domain.exception.RegraNegocioException;
import com.br.sigaf.domain.repository.UserRepository;
import com.br.sigaf.domain.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;


    @Override
    public User autenticar(String email, String senha) {
        Optional<User> usuario = repository.findByEmail(email);

        if (usuario.isEmpty()) {
            throw new AuthenticationError("Usuário não encontrado para o email informado.");
        }

        boolean senhasBatem = encoder.matches(senha, usuario.get().getPassword());

        if (!senhasBatem) {
            throw new AuthenticationError("Senha inválida.");
        }

        return usuario.get();
    }

    @Override
    public void validarEmail(String email) {
        boolean existe = repository.existsByEmail(email);
        if (existe) {
            throw new RegraNegocioException("Já existe um usuário cadastrado com este email.");
        }
    }

    @Override
    public void criptografarSenha(User usuario) {
        String senha = usuario.getPassword();
        String senhaCripto = encoder.encode(senha);
        usuario.setPassword(senhaCripto);
    }
}
