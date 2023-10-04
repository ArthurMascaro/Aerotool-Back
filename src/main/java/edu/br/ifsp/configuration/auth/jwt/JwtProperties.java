package edu.br.ifsp.configuration.auth.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class JwtProperties {

    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationAfterMinutes;
    private Integer refreshTokenExpirationAfterDays;

    public JwtProperties() {
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public Integer getTokenExpirationAfterMinutes() {
        return tokenExpirationAfterMinutes;
    }

    public void setTokenExpirationAfterMinutes(Integer tokenExpirationAfterMinutes) {
        this.tokenExpirationAfterMinutes = tokenExpirationAfterMinutes;
    }

    public Integer getRefreshTokenExpirationAfterDays() {
        return refreshTokenExpirationAfterDays;
    }

    public void setRefreshTokenExpirationAfterDays(Integer refreshTokenExpirationAfterDays) {
        this.refreshTokenExpirationAfterDays = refreshTokenExpirationAfterDays;
    }

    @Bean
    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }

    @Bean
    public String getRefreshTokenProperty() {
        return "refresh-token";
    }
}
