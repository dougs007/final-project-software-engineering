package com.br.sigaf.domain.service.impl;

import com.br.sigaf.domain.entity.User;
import com.br.sigaf.domain.exception.AuthenticationError;
import com.br.sigaf.domain.exception.RegraNegocioException;
import com.br.sigaf.domain.repository.UserRepository;
import com.br.sigaf.domain.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(
            UserRepository repository,
            PasswordEncoder encoder) {
        super();
        this.repository = repository;
        this.encoder = encoder;
    }

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
    public User salvarUsuario(User usuario) {
        validarEmail(usuario.getEmail());
        criptografarSenha(usuario);
        return repository.save(usuario);
    }

    @Override
    public void validarEmail(String email) {
        boolean existe = repository.existsByEmail(email);
        if (existe) {
            throw new RegraNegocioException("Já existe um usuário cadastrado com este email.");
        }
    }

    @Override
    public Optional<User> obterPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<User> getAll() {
        return this.repository.findAll();
    }

    private void criptografarSenha(User usuario) {
        String senha = usuario.getPassword();
        String senhaCripto = encoder.encode(senha);
        usuario.setPassword(senhaCripto);
    }
}
