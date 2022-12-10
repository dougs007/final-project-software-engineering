package com.br.sigaf.domain.service;


import com.br.sigaf.domain.dto.UserDTO;
import com.br.sigaf.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<User> getAll();
    User createStudent(UserDTO userDTO);
    Optional<User> getById(Long id);
    User update(UserDTO dto, Long id);
    void delete(Long id);
}

