package edu.br.ifsp.configuration.auth.security;

import edu.br.ifsp.configuration.auth.jwt.JwtProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcCorsConfiguration implements WebMvcConfigurer {

    private final JwtProperties jwtProperties;

    public WebMvcCorsConfiguration(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowCredentials(true)
                .exposedHeaders(jwtProperties.getAuthorizationHeader());
    }
}
