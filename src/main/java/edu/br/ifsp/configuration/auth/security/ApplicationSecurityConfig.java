package edu.br.ifsp.configuration.auth.security;

import edu.br.ifsp.configuration.auth.jwt.JwtAuthenticationFilter;
import edu.br.ifsp.configuration.auth.jwt.JwtProperties;
import edu.br.ifsp.configuration.auth.jwt.JwtTokenHelper;
import edu.br.ifsp.configuration.auth.jwt.JwtTokenVerifier;
import edu.br.ifsp.domain.usecases.user.ApplicationUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;
import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder encoder;
    private final ApplicationUserService applicationUserService;
    private final SecretKey secretKey;
    private final JwtProperties jwtProperties;
    private final JwtTokenHelper jwtTokenHelper;

    public ApplicationSecurityConfig(PasswordEncoder encoder,
                                     ApplicationUserService applicationUserService,
                                     SecretKey secretKey,
                                     JwtProperties jwtProperties, JwtTokenHelper jwtTokenHelper) {
        this.encoder = encoder;
        this.applicationUserService = applicationUserService;
        this.secretKey = secretKey;
        this.jwtProperties = jwtProperties;
        this.jwtTokenHelper = jwtTokenHelper;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter((Filter) new JwtAuthenticationFilter(authenticationManager(), jwtProperties, jwtTokenHelper))
                .addFilterAfter((Filter) new JwtTokenVerifier(jwtProperties, jwtTokenHelper), JwtAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/refresh-token").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/api/v1/**").authenticated()
                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

}
