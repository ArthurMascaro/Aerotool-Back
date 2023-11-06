package edu.br.ifsp.domain.usecases.user;

import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.utils.Validator;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FindUserUseCase {

    private UserDAO userDAO;

    public FindUserUseCase(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Optional<User> findOne(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null!");
        }
        return userDAO.findOne(user.getPromptuary());
    }

    public Optional<User> findByPromptuary(String promptuary) {
        if (promptuary == null) {
            throw new IllegalArgumentException("Promptuary cannot be null");
        }
        return userDAO.findByPromptuary(promptuary);
    }

    public List<User> findAll() {
        return userDAO.findALL();
    }
}
