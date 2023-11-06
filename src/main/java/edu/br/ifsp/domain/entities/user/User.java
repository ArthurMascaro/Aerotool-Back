package edu.br.ifsp.domain.entities.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class User implements UserDetails {

    private UUID id;
    private String name;
    private Role role;
    private String promptuary;
    private String password;

    private Collection<? extends GrantedAuthority> authorities = Collections.emptyList();

    private boolean isAccountNonExpired;

    private boolean isAccountNonLocked;

    private boolean isCredentialsNonExpired;

    private boolean isEnabled;

    public User() {
        this.id = UUID.randomUUID();
    }

    public User(UUID id, String name, Role role, String promptuary) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.promptuary = promptuary;
    }

    public User(UUID id, String name, Role role, String promptuary, String password, Collection<? extends GrantedAuthority> authorities, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.promptuary = promptuary;
        this.password = password;
        this.authorities = authorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public User(UUID id, String name, Role role, String promptuary, String password, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.promptuary = promptuary;
        this.password = password;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public User(UUID id, String name, Role role, String promptuary, String password) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.promptuary = promptuary;
        this.password = password;
    }

    public User(String name, String promptuary, String password) {
        this.name = name;
        this.promptuary = promptuary;
        this.password = password;
    }

    public User createWithId(UUID id) {
        return new User(id, name, role, promptuary, password, authorities, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
    }

    public User(UUID id) {
        if (id == null)
            throw new IllegalArgumentException("id cannot be null");
        this.id = id;
    }

    public User(String promptuary) {
        this.promptuary = promptuary;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPromptuary() {
        return promptuary;
    }

    public void setPromptuary(String promptuary) {
        this.promptuary = promptuary;
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
        return name;
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
