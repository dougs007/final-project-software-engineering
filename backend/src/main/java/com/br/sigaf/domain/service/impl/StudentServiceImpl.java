package com.br.sigaf.domain.service.impl;

import com.br.sigaf.domain.dto.UnityDTO;
import com.br.sigaf.domain.dto.UserDTO;
import com.br.sigaf.domain.entity.User;
import com.br.sigaf.domain.enumerates.GenderEnum;
import com.br.sigaf.domain.enumerates.RoleEnum;
import com.br.sigaf.domain.exception.RegraNegocioException;
import com.br.sigaf.domain.repository.UserRepository;
import com.br.sigaf.domain.service.StudentService;
import com.br.sigaf.domain.service.UnityService;
import com.br.sigaf.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final UserRepository repository;
    private final UserService userService;
    private final UnityService unityService;

    @Override
    public List<UserDTO> getAll() {
        List<User> users = this.repository.getStudents();

        return users.stream().map(student -> {
            UnityDTO unityDto = null != student.getUnityId()
                    ? UnityDTO.parse(unityService.getById(student.getUnityId()).get())
                    : null;
            UserDTO studentDto = this.repository.getNameByUserId(student.getUserId());

            return UserDTO.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .celphone(student.getCelphone())
                    .gender(GenderEnum.toEnum(student.getGenderId()).getNamePt())
                    .genderId(student.getGenderId())
                    .unity(unityDto)
                    .roleId(RoleEnum.ROLE_STUDENT.getCode())
                    .coach(studentDto)
                    .email(student.getEmail())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public User createStudent(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .celphone(userDTO.getCelphone())
                .genderId(null != userDTO.getGenderId() ? userDTO.getGenderId() : GenderEnum.OTHERS.getCode())
                .roleId(RoleEnum.ROLE_STUDENT.getCode())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .userId(userDTO.getUserId())
                .unityId(userDTO.getUnityId())
                .build();

        return this.userService.createUser(user);
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
                .userId(null != userDTO.getUserId() ? userDTO.getUserId() :  user.getUserId())
                .unityId(null != userDTO.getUnityId() ? userDTO.getUnityId() :  user.getUnityId())
                .build();
        return this.repository.saveAndFlush(userUpdate);
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
