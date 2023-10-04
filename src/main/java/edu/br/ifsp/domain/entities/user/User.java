package edu.br.ifsp.domain.entities.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class User implements UserDetails {

    private UUID id;
    private String nome;

    private String password;
    private Role role;
    private Promptuary promptuary;

    private Collection<? extends GrantedAuthority> authorities = Collections.emptyList();

    private boolean isAccountNonExpired;

    private boolean isAccountNonLocked;

    private boolean isCredentialsNonExpired;

    private boolean isEnabled;

    public User() {
        this.id = UUID.randomUUID();
    }

    public User(UUID id, String nome, Role role, Promptuary promptuary) {
        this.id = id;
        this.nome = nome;
        this.role = role;
        this.promptuary = promptuary;
    }

    public User(UUID id) {
        if (id == null)
            throw new IllegalArgumentException("id cannot be null");
        this.id = id;
    }

    public User(Promptuary promptuary) {
        this.promptuary = promptuary;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Promptuary getPromptuary() {
        return promptuary;
    }

    public void setPromptuary(Promptuary promptuary) {
        this.promptuary = promptuary;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", role=" + role +
                ", promptuary=" + promptuary +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.nome;
    }

    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
