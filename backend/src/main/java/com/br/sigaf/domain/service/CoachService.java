package com.br.sigaf.domain.service;


import com.br.sigaf.domain.dto.UnityDTO;
import com.br.sigaf.domain.dto.UserDTO;
import com.br.sigaf.domain.entity.Unity;
import com.br.sigaf.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface CoachService {

    List<User> getAll();
    User save(UserDTO userDTO);
    Optional<User> getById(Long id);
    User update(UserDTO dto, Long id);
    void delete(Long id);
}

