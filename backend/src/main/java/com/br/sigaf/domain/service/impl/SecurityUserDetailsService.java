package com.br.sigaf.domain.service.impl;

import com.br.sigaf.domain.entity.User;
import com.br.sigaf.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User usuarioEncontrado = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found."));

        return org.springframework.security.core.userdetails.User.builder()
                .username(usuarioEncontrado.getEmail())
                .password(usuarioEncontrado.getPassword())
                .roles("USER")
                .build();
    }
}
