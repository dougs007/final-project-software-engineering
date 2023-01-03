package com.br.sigaf.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Table(name = "tb_user")
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String celphone;
    private LocalDate hiringDate;
    private Long codeCref;
    private Long codeMatricula;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    //    @Column(columnDefinition = "default " + GenderEnum.OTHERS.getCode())
    @Column(columnDefinition = "integer default " + 1)
    private Integer genderId;
    //    @Column(columnDefinition = "default " + RoleEnum.ROLE_STUDENT.getCode())
    @Column(columnDefinition = "integer default " + 3)
    private Integer roleId;
    private Long userId;

    private Long unityId;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private Set<Alert> alerts = new HashSet<>();

    @OneToMany(mappedBy="student", fetch = FetchType.LAZY)
    private Set<Evaluation> evaluations;

    @OneToMany(mappedBy="student", fetch = FetchType.LAZY)
    private Set<Appearance> appearances;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private Set<Signature> signatures;

    @OneToMany(mappedBy="student", fetch = FetchType.LAZY)
    private Set<Exercise> exercises;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
