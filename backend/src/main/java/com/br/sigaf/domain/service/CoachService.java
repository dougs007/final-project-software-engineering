package com.br.sigaf.domain.service;


import com.br.sigaf.domain.dto.UserDTO;
import com.br.sigaf.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface CoachService {

    List<UserDTO> getAll();
    User createCoach(UserDTO userDTO);
    Optional<User> getById(Long id);
    UserDTO getCoachById(Long id);
    User update(UserDTO dto, Long id);
    void delete(Long id);
}

