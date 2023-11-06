package edu.br.ifsp.domain.entities.user;

import edu.br.ifsp.domain.usecases.user.UserDAO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.findByName(username)
                .orElseThrow(() -> new RuntimeException("Couldn't find user with username " + username));
    }

    public User findUserById(UUID userId) {
        return userDAO.findByUUID(userId)
                .orElseThrow(() -> new RuntimeException("Couldn't find user with uuid " + userId));
    }

    public User findUserByPromptuary(String promptuary) {
        return userDAO.findByPromptuary(promptuary)
                .orElseThrow(() -> new RuntimeException("Couldn't find user with promptuary " + promptuary));
    }



}
