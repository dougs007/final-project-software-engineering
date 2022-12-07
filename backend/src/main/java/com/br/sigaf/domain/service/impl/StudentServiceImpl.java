package com.br.sigaf.domain.service.impl;

import com.br.sigaf.domain.dto.UserDTO;
import com.br.sigaf.domain.entity.User;
import com.br.sigaf.domain.enumerates.GenderEnum;
import com.br.sigaf.domain.enumerates.RoleEnum;
import com.br.sigaf.domain.exception.RegraNegocioException;
import com.br.sigaf.domain.repository.UserRepository;
import com.br.sigaf.domain.service.CoachService;
import com.br.sigaf.domain.service.StudentService;
import com.br.sigaf.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final UserRepository repository;
    private final UserService userService;

    @Override
    public List<User> getAll() {
        return this.repository.getStudents();
    }

    @Override
    public User save(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .celphone(userDTO.getCelphone())
                .genderId(null != userDTO.getGenderId() ? userDTO.getGenderId() : GenderEnum.OTHERS.getCode())
                .roleId(RoleEnum.ROLE_STUDENT.getCode())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .userId(userDTO.getUserId())
                .build();

        return this.userService.salvarUsuario(user);
    }

    @Override
    public Optional<User> getById(Long id) {
        return Optional.ofNullable(this.repository.findById(id).orElseThrow(
                () -> new RegraNegocioException("Recurso não encontrado")
        ));
    }

    @Override
    public User update(UserDTO userDTO, Long id) {
        User user = this.getById(id).get();

        User userUpdate = User.builder()
                .id(id)
                .name(userDTO.getName())
                .celphone(userDTO.getCelphone())
                .genderId(null != userDTO.getGenderId() ? userDTO.getGenderId() : GenderEnum.OTHERS.getCode())
                .roleId(RoleEnum.ROLE_STUDENT.getCode())
                .email(userDTO.getEmail())
                .password(user.getPassword())
                .userId(userDTO.getUserId())
                .build();
        return this.repository.saveAndFlush(userUpdate);
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
