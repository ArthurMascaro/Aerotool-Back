package edu.br.ifsp.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.br.ifsp.configuration.auth.jwt.JwtProperties;
import edu.br.ifsp.configuration.auth.jwt.JwtTokenHelper;
import edu.br.ifsp.domain.entities.user.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(path = "/refresh-token")
public class RefreshTokenController {
    private final UserService userService;
    private final JwtProperties jwtProperties;
    private final JwtTokenHelper jwtTokenHelper;

    public RefreshTokenController(UserService userService, JwtProperties jwtProperties, JwtTokenHelper jwtTokenHelper) {
        this.userService = userService;
        this.jwtProperties = jwtProperties;
        this.jwtTokenHelper = jwtTokenHelper;
    }

    @GetMapping
    public void getNewAccessToken(
            @CookieValue(value = "refresh-token", defaultValue = "") String refreshToken,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        if (Strings.isEmpty(refreshToken)) {
            final String error = "Refresh token is missing or invalid.";
            log.error("Refresh token verification error: {}", error);
            response.addHeader(jwtProperties.getAuthorizationHeader(), error);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        final String jwtToken = refreshToken.replace(jwtProperties.getTokenPrefix(), "");
        try {
            final var principal = jwtTokenHelper.extractClaims(jwtToken).getSubject();
            final var userId = UUID.fromString(principal);

            final var user = (User) userService.findUserById(userId);
            final var issuer = request.getRequestURL().toString();
            final var token = jwtTokenHelper.createAccessToken(user, issuer);

            response.addHeader(jwtProperties.getAuthorizationHeader(), jwtProperties.getTokenPrefix() + token);

            final Map<String, String> body = new HashMap<>();
            body.put("uuid", user.getId().toString());
            body.put("promptuary", user.getPromptuary());
            body.put("name", user.getName());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), body);

            log.info("Refreshed access token for: {}", user.getUsername());
        } catch (Exception e) {
            log.error("Refresh token verification error: {}", e.getMessage());
            jwtTokenHelper.configureTokenErrorResponse(response, e.getMessage());
        }
    }

    @DeleteMapping
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authorizationHeader = request.getHeader(jwtProperties.getAuthorizationHeader());
        if (jwtTokenHelper.hasInvalidAuthorization(authorizationHeader)) {
            final String error = "Authorization header is missing or invalid.";
            log.error("Token verification error: {}", error);
            response.addHeader(jwtProperties.getAuthorizationHeader(), error);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        final String jwt = authorizationHeader.replace(jwtProperties.getTokenPrefix(), "");
        try {
            final var principal = jwtTokenHelper.extractClaims(jwt).getSubject();
            final var userId = UUID.fromString(principal);
            final var user = (User) userService.findUserById(userId);

            Cookie expiredToken = new Cookie(jwtProperties.getRefreshTokenProperty(), "");
            expiredToken.setHttpOnly(true);
            expiredToken.setMaxAge(0);
            expiredToken.setPath("/");

            response.addCookie(expiredToken);
            response.setStatus(HttpStatus.NO_CONTENT.value());

            log.info("User {} has been logged out.", user.getName());
        } catch (Exception e) {
            log.error("Token verification error: {}", e.getMessage());
            jwtTokenHelper.configureTokenErrorResponse(response, e.getMessage());
        }
    }

}

