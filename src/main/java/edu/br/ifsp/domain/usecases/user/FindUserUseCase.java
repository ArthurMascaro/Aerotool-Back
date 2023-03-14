package edu.br.ifsp.domain.usecases.user;

import edu.br.ifsp.domain.entities.user.Promptuary;
import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.utils.Validator;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Optional<User> findByPromptuary(Promptuary promptuary) {
        if (promptuary == null) {
            throw new IllegalArgumentException("Promptuary cannot be null");
        }
        return userDAO.findByPromptuary(promptuary);
    }

    public List<User> findAll() {
        return userDAO.findALL();
    }
}
