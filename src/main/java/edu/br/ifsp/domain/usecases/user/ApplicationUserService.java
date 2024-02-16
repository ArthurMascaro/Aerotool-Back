package edu.br.ifsp.domain.usecases.user;

import edu.br.ifsp.domain.entities.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final UserDAO applicationUserDAO;

    public ApplicationUserService(UserDAO applicationUserDAO) {
        this.applicationUserDAO = applicationUserDAO;
    }
    @Override
    public UserDetails loadUserByUsername(String promptuary) throws UsernameNotFoundException {
        return applicationUserDAO.findByPromptuary(promptuary)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with promptuary %s not found", promptuary)));
    }

    public User findUserById(UUID userId) {
        return applicationUserDAO.findByUUID(userId)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with id %s not found", userId)));
    }
}
