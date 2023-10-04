package edu.br.ifsp.configuration.auth.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtSecretKey{

    private final JwtProperties properties;

    public JwtSecretKey(JwtProperties properties) {
        this.properties = properties;
    }

    @Bean
    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(properties.getSecretKey().getBytes());
    }
}