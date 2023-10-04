package edu.br.ifsp.configuration.auth.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtProperties jwtProperties;
    private final JwtTokenHelper jwtTokenHelper;

    public JwtTokenVerifier(JwtProperties jwtProperties, JwtTokenHelper jwtTokenHelper) {
        this.jwtProperties = jwtProperties;
        this.jwtTokenHelper = jwtTokenHelper;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if(isFromPermittedPath(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authorizationHeader = request.getHeader(jwtProperties.getAuthorizationHeader());
        if (jwtTokenHelper.hasInvalidAuthorization(authorizationHeader)) {
            final String error = "Authorization header is missing or invalid.";
            log.error("Token verification error: {}", error);
            response.addHeader(jwtProperties.getAuthorizationHeader(), error);
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authorizationHeader.replace(jwtProperties.getTokenPrefix(), "");
        try {
            final String principal = jwtTokenHelper.extractClaims(token).getSubject();
            final UUID userId = UUID.fromString(principal);
            final var authentication = new UsernamePasswordAuthenticationToken(userId, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            log.error("Token verification error: {}", e.getMessage());
            jwtTokenHelper.configureTokenErrorResponse(response, e.getMessage());
        }
    }

    private boolean isFromPermittedPath(HttpServletRequest request) {
        return request.getServletPath().equals("/register")
                || request.getServletPath().equals("/login")
                || request.getServletPath().equals("/refresh-token");
    }

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        try {
            doFilterInternal((HttpServletRequest) request, (HttpServletResponse) response, (FilterChain) filterChain);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
