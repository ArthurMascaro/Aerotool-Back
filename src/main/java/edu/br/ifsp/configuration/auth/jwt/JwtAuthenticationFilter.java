package edu.br.ifsp.configuration.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.br.ifsp.domain.entities.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtProperties jwtProperties;
    private final JwtTokenHelper jwtTokenHelper;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
                                   JwtProperties jwtProperties, JwtTokenHelper jwtTokenHelper) {
        this.authenticationManager = authenticationManager;
        this.jwtProperties = jwtProperties;
        this.jwtTokenHelper = jwtTokenHelper;
    }

    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {

        try {
            final var authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), AuthenticationRequest.class);

            final var authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getName(),
                    authenticationRequest.getPassword()
            );
            return authenticationManager.authenticate(authentication);

        } catch (IOException e) {
            response.addHeader(jwtProperties.getAuthorizationHeader(), "Invalid username or password");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return null;
        }
    }

    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        var user = (User) authResult.getPrincipal();
        final String issuer = request.getRequestURL().toString();
        final String token = jwtTokenHelper.createAccessToken(user, issuer);
        response.addHeader(jwtProperties.getAuthorizationHeader(), jwtProperties.getTokenPrefix() + token);

        final Cookie refreshToken = jwtTokenHelper.createRefreshTokenCookie(user, issuer);
        response.addCookie(refreshToken);

        final Map<String, String> body = new HashMap<>();
        body.put("uuid", user.getId().toString());
        body.put("name", user.getUsername());
        body.put("promptuary", user.getPromptuary());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), body);
    }
}