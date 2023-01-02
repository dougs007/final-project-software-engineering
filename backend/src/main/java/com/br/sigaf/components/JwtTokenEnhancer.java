package com.br.sigaf.components;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.br.sigaf.domain.entity.User;
import com.br.sigaf.domain.repository.UserRepository;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;


@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    private final UserRepository userRepository;

    public JwtTokenEnhancer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Optional<User> userOpt = userRepository.findByEmail(authentication.getName());

        Map<String, Object> map = new HashMap<>();
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            map.put("userName", user.getName());
            map.put("userId", user.getId());
        }

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        token.setAdditionalInformation(map);

        return accessToken;
    }
}
