package com.br.sigaf.domain.service.impl;

import com.br.sigaf.domain.entity.User;
import com.br.sigaf.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public SecurityUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
