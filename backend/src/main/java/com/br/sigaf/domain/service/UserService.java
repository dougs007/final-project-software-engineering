package com.br.sigaf.domain.service;

import com.br.sigaf.domain.dto.UserDTO;
import com.br.sigaf.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User usuario);

    Optional<User> getById(Long id);

    List<UserDTO> getAll();

}
