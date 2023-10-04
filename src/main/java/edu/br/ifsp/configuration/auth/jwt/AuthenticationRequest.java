package edu.br.ifsp.configuration.auth.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AuthenticationRequest {
    private String username;
    private String password;
}
