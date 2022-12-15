package com.br.sigaf.domain.service.impl;

import com.br.sigaf.domain.dto.UnityDTO;
import com.br.sigaf.domain.dto.UserDTO;
import com.br.sigaf.domain.entity.User;
import com.br.sigaf.domain.enumerates.GenderEnum;
import com.br.sigaf.domain.enumerates.RoleEnum;
import com.br.sigaf.domain.exception.RegraNegocioException;
import com.br.sigaf.domain.repository.UserRepository;
import com.br.sigaf.domain.service.AuthService;
import com.br.sigaf.domain.service.CoachService;
import com.br.sigaf.domain.service.UnityService;
import com.br.sigaf.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CoachServiceImpl implements CoachService {

    private final UserRepository repository;
    private final UserService userService;
    private final UnityService unityService;
    private final AuthService authService;

    @Override
    public List<UserDTO> getAll() {
        List<User> coaches = this.repository.getCoaches();
        return coaches.stream().map(coach -> {
            UnityDTO unityDto = null != coach.getUnityId()
                    ? UnityDTO.parse(unityService.getById(coach.getUnityId()).get())
                    : null;

            return UserDTO.builder()
                    .id(coach.getId())
                    .name(coach.getName())
                    .celphone(coach.getCelphone())
                    .gender(GenderEnum.toEnum(coach.getGenderId()).getNamePt())
                    .genderId(coach.getGenderId())
                    .codeCref(coach.getCodeCref())
                    .unity(unityDto)
                    .roleId(RoleEnum.ROLE_COACH.getCode())
                    .email(coach.getEmail())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public User createCoach(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .celphone(userDTO.getCelphone())
                .genderId(null != userDTO.getGenderId() ? userDTO.getGenderId() : GenderEnum.OTHERS.getCode())
                .roleId(RoleEnum.ROLE_COACH.getCode())
                .unityId(userDTO.getUnityId())
                .codeCref(userDTO.getCodeCref())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .userId(null)
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
    public UserDTO getCoachById(Long id) {
        User user = this.getById(id).get();

        UnityDTO unityDto = null != user.getUnityId()
                ? UnityDTO.parse(unityService.getById(user.getUnityId()).get())
                : null;

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .celphone(user.getCelphone())
                .gender(GenderEnum.toEnum(user.getGenderId()).getNamePt())
                .genderId(user.getGenderId())
                .codeCref(user.getCodeCref())
                .unity(unityDto)
                .roleId(RoleEnum.ROLE_COACH.getCode())
                .email(user.getEmail())
                .build();
    }

    @Override
    public User update(UserDTO userDTO, Long id) {
        User user = this.getById(id).get();

        String passwordUpdated = null != userDTO.getPassword()
                ? authService.hashPassword(userDTO.getPassword())
                : user.getPassword();

        User userUpdate = User.builder()
                .id(id)
                .name(userDTO.getName())
                .celphone(userDTO.getCelphone())
                .genderId(null != userDTO.getGenderId() ? userDTO.getGenderId() : GenderEnum.OTHERS.getCode())
                .unityId(null != userDTO.getUnityId() ? userDTO.getUnityId() : null)
                .roleId(RoleEnum.ROLE_COACH.getCode())
                .email(userDTO.getEmail())
                .codeCref(userDTO.getCodeCref())
                .password(passwordUpdated)
                .userId(null)
                .build();
        return this.repository.saveAndFlush(userUpdate);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
