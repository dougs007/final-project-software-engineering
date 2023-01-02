package com.br.sigaf.domain.repository;

import com.br.sigaf.domain.dto.UserDTO;
import com.br.sigaf.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    //    RoleEnum.ROLE_COACH
    @Query(" from User a where a.roleId = 2")
    List<User> getCoaches();

    //    RoleEnum.ROLE_STUDENT
    @Query(" from User a where a.roleId = 3")
    List<User> getStudents();

    @Query(" SELECT new com.br.sigaf.domain.dto.UserDTO(a.name) FROM User a where a.id = ?1")
    UserDTO getNameByUserId(Long userId);
}
